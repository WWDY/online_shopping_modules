package com.wwdy.front.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwdy.front.constant.AliPayConstant;
import com.wwdy.front.feign.AdminClient;
import com.wwdy.front.feign.AuthClient;
import com.wwdy.front.feign.pojo.dto.Shop;
import com.wwdy.front.feign.pojo.dto.User;
import com.wwdy.front.pojo.Cart;
import com.wwdy.front.pojo.Order;
import com.wwdy.front.pojo.OrderSnapshot;
import com.wwdy.front.pojo.OrderUserInfo;
import com.wwdy.front.pojo.dto.AlipayRequest;
import com.wwdy.front.pojo.dto.ShopPayInfo;
import com.wwdy.front.service.AlipayService;
import com.wwdy.front.service.CartService;
import com.wwdy.front.service.OrderService;
import com.wwdy.front.service.OrderUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import result.vo.ResultVO;
import utils.RequestUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wwdy
 * @date 2022/4/8 20:50
 */
@RequiredArgsConstructor
@Service
public class AlipayServiceImpl implements AlipayService {

    private static final String SSO_TOKEN = "SSO-Token";

    private final AdminClient adminClient;

    private final AlipayClient alipayClient;

    private final ObjectMapper objectMapper;

    private final OrderService orderService;

    private final OrderUserInfoService orderUserInfoService;

    private final OrderSnapshotServiceImpl orderSnapshotService;

    private final AuthClient authClient;

    private final CartService cartService;

    /**
     * 创建订单
     *
     * @param shopPayInfos 商品id
     * @return String
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String pay(ShopPayInfo shopPayInfos) {
        List<Integer> ids = shopPayInfos
                .getShopPayInfoList()
                .stream()
                .map(ShopPayInfo.ShopInfo::getId)
                .collect(Collectors.toList());
        List<Shop> shopList = adminClient.getShopListByIds(ids).getData();
        Map<Integer, List<ShopPayInfo.ShopInfo>> listMap = shopPayInfos
                .getShopPayInfoList()
                .stream()
                .collect(Collectors.groupingBy(ShopPayInfo.ShopInfo::getId));
        double totalMoney = shopList.stream()
                .map(shop ->
                        shop.getDiscountPrice() * listMap.get(shop.getId()).get(0).getCount()
                )
                .mapToDouble(Double::valueOf)
                .sum();
        LocalDateTime now = LocalDateTime.now();
        String orderId = now.getYear() +  String.format("%02d",now.getMonthValue()) + String.format("%02d",now.getDayOfMonth())  + System.currentTimeMillis();
        try {
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            AlipayRequest alipayRequest = new AlipayRequest();
            alipayRequest.setOutTradeNo(orderId);
            alipayRequest.setTotalAmount(totalMoney);
            alipayRequest.setSubject("WWDY商城");
            request.setBizContent(objectMapper.writeValueAsString(alipayRequest));
            request.setReturnUrl("http://localhost:10000/api/front/alipay/return");
            generateOrder(shopList,orderId,shopPayInfos);
            return alipayClient.pageExecute(request).getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 校验订单支付状态
     * @param requestParams 回调参数
     * @return boolean
     */
    @Override
    public boolean checkPay(Map<String,String> requestParams) {
        try {
            boolean checkV1 = AlipaySignature.rsaCheckV1(requestParams, AliPayConstant.PUBLIC_KEY, AliPayConstant.CHARSET, AliPayConstant.SIGN_TYPE);
            String outTradeNo = requestParams.get("out_trade_no");
            String tradeNo = requestParams.get("trade_no");
            if(checkV1){
                Order order = Order.builder()
                        .id(outTradeNo)
                        .status(true)
                        .alipayOrder(tradeNo)
                        .build();
                finishOrder(outTradeNo);
                return orderService.updateById(order);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 完成订单
     * @param orderId 订单id
     */
    protected void finishOrder(String orderId){
        Order order = orderService.getById(orderId);
        String shopId = order.getShopId();
        List<String> ids = Arrays.asList(shopId.split(","));
        List<OrderSnapshot> orderSnapshots = orderSnapshotService.list(new QueryWrapper<OrderSnapshot>().eq("order_id", orderId));
        Map<Integer, List<OrderSnapshot>> listMap = orderSnapshots.stream().collect(Collectors.groupingBy(OrderSnapshot::getShopId));
        List<Integer> idsInteger = ids.stream().map(Integer::valueOf).collect(Collectors.toList());
        ResultVO<List<Shop>> shopListByIds = adminClient.getShopListByIds(idsInteger);
        List<Shop> shops = shopListByIds.getData();
        List<Shop> resShops = shops.stream().peek(shop -> {
            shop.setStock(shop.getStock() - listMap.get(shop.getId()).get(0).getShopCount());
            shop.setSales(shop.getSales() + listMap.get(shop.getId()).get(0).getShopCount());
        }).collect(Collectors.toList());
        ResultVO<String> resultVO = adminClient.updateShopList(resShops);
        if (resultVO.getCode() != 0) {
            adminClient.updateShopList(shops);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(SSO_TOKEN)){
                token = cookie.getValue();
            }
        }
        User user = authClient.getUserInfoByToken(token).getData();
        cartService.remove(new QueryWrapper<Cart>().eq("user_id",user.getId()).in("shop_id",ids));
    }

    /**
     * 生成订单信息
     * @param shopList 商品信息
     * @param orderId 订单id
     * @param shopPayInfos 商品信息
     */
    protected void generateOrder(List<Shop> shopList, String orderId, ShopPayInfo shopPayInfos){
        Map<Integer, List<ShopPayInfo.ShopInfo>> listMap = shopPayInfos
                .getShopPayInfoList()
                .stream()
                .collect(Collectors.groupingBy(ShopPayInfo.ShopInfo::getId));
        //订单快照信息
        List<OrderSnapshot> orderSnapshots = shopList.stream().map(shop ->
                OrderSnapshot.builder()
                        .orderId(orderId)
                        .shopId(shop.getId())
                        .shopCount(listMap.get(shop.getId()).get(0).getCount())
                        .shopTitle(shop.getTitle())
                        .shopPrice(shop.getPrice())
                        .shopDiscountPrice(shop.getDiscountPrice())
                        .build()
        ).collect(Collectors.toList());
        //订单信息
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = authClient.getUserInfoByToken(RequestUtil.getRequestToken(request)).getData();
        List<String> ids = shopList.stream().map(shop -> String.valueOf(shop.getId())).collect(Collectors.toList());
        Order order = Order.builder()
                .id(orderId)
                .shopId(String.join(",", ids))
                .status(false)
                .userId(user.getId())
                .build();
        OrderUserInfo orderUserInfo = OrderUserInfo.builder()
                .orderId(orderId)
                .name(shopPayInfos.getName())
                .address(shopPayInfos.getAddress())
                .phone(shopPayInfos.getPhone())
                .build();
        orderService.insertOrder(order);
        orderUserInfoService.insertOrderUserInfo(orderUserInfo);
        orderSnapshotService.insertSnapshot(orderSnapshots);
    }
}
