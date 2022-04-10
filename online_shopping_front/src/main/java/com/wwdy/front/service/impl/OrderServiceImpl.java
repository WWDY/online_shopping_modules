package com.wwdy.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.front.feign.AuthClient;
import com.wwdy.front.feign.pojo.dto.User;
import com.wwdy.front.mapper.OrderMapper;
import com.wwdy.front.pojo.Order;
import com.wwdy.front.pojo.OrderSnapshot;
import com.wwdy.front.pojo.OrderUserInfo;
import com.wwdy.front.pojo.vo.OrderVO;
import com.wwdy.front.service.OrderService;
import com.wwdy.front.service.OrderSnapshotService;
import com.wwdy.front.service.OrderUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author  wwdy
 * @date  2022/4/9 14:29
 */
@RequiredArgsConstructor
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

    private final AuthClient authClient;

    private final OrderSnapshotService orderSnapshotService;

    private final OrderUserInfoService orderUserInfoService;

    /**
     * 添加订单
     * @param order 订单
     */
    @Override
    public void insertOrder(Order order) {
        baseMapper.insert(order);
    }

    /**
     * 获取用户订单信息
     * @return List<OrderVO>
     */
    @Override
    public List<OrderVO> getUserOrder() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = RequestUtil.getRequestToken(request);
        User user = authClient.getUserInfoByToken(token).getData();
        List<Order> orders = baseMapper.selectList(new QueryWrapper<Order>().eq("user_id", user.getId()));
        return orders.stream().map(order -> {
            List<OrderSnapshot> snapshots = orderSnapshotService.list(new QueryWrapper<OrderSnapshot>().eq("order_id", order.getId()));
            OrderVO orderVO = new OrderVO();
            orderVO.setOrderId(order.getId());
            orderVO.setSnapshots(snapshots);
            orderVO.setPayStatus(StrUtil.isNotEmpty(order.getAlipayOrder()));
            orderVO.setCreatedTime(order.getCreatedTime());
            return orderVO;
        }).collect(Collectors.toList());
    }

    /**
     * 分页查找订单
     *
     * @param page 查询信息
     * @return Page<Order>
     */
    @Override
    public Page<OrderVO> selectOrderPage(com.wwdy.front.feign.pojo.dto.Page page) {
        Page<Order> orderPage = new Page<>();
        orderPage.setSize(page.getSize());
        orderPage.setCurrent(page.getPage());
        Page<Order> order = baseMapper.selectPage(orderPage, null);
        List<Order> records = order.getRecords();
        List<OrderVO> orderVOList = records.stream().map(orderInfo -> {
            List<OrderSnapshot> snapshots = orderSnapshotService.list(new QueryWrapper<OrderSnapshot>().eq("order_id", orderInfo.getId()));
            OrderVO orderVO = new OrderVO();
            orderVO.setOrderId(orderInfo.getId());
            orderVO.setSnapshots(snapshots);
            orderVO.setPayStatus(StrUtil.isNotEmpty(orderInfo.getAlipayOrder()));
            orderVO.setCreatedTime(orderInfo.getCreatedTime());
            return orderVO;
        }).collect(Collectors.toList());
        return new Page<OrderVO>(orderPage.getCurrent(), orderPage.getSize(), orderPage.getTotal()).setRecords(orderVOList);
    }

    /**
     * 通过订单号删除订单
     * @param orderId 订单号
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOrderById(String orderId) {
        baseMapper.deleteById(orderId);
        orderSnapshotService.remove(new QueryWrapper<OrderSnapshot>().eq("order_id",orderId));
        orderUserInfoService.remove(new QueryWrapper<OrderUserInfo>().eq("order_id",orderId));
        return true;
    }
}




