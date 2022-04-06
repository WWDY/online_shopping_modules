package com.wwdy.front.service.impl;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.front.exception.NotFoundRecordException;
import com.wwdy.front.feign.AuthClient;
import com.wwdy.front.feign.pojo.dto.Page;
import com.wwdy.front.feign.pojo.dto.Shop;
import com.wwdy.front.feign.pojo.dto.User;
import com.wwdy.front.mapper.ShopMapper;
import com.wwdy.front.pojo.vo.ShopDetailVO;
import com.wwdy.front.pojo.vo.ShopListVO;
import com.wwdy.front.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import result.vo.PageVO;
import utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wwdy
 * @date 2022/4/3 16:26
 */
@Service
@RequiredArgsConstructor
public class ShopServiceImpl extends ServiceImpl<ShopMapper,Shop> implements ShopService  {

    private final AuthClient authClient;


    /**
     * 查询商品列表
     * @param page 分页对象
     * @return PageVO<ShopListVO>
     */
    @Override
    public PageVO<ShopListVO> getShopList(Page page) {
        page.setOffset((page.getPage() - 1) * page.getSize());
        List<ShopListVO> shopListVOList = baseMapper.selectPageShopList(page);
        List<ShopListVO> resList = shopListVOList.stream().peek(shopListVO -> {
            shopListVO.setSliderShow(Arrays.asList(shopListVO.getSliderShows().split(",")));
        }).collect(Collectors.toList());
        long total = baseMapper.totalShopList();
        return PageVO.of(resList, page.getPage(), page.getSize(),total);
    }

    /**
     * 通过id获取商品详情
     * @param id id
     * @return ShopListVO
     */
    @Override
    public ShopDetailVO getShopDetailById(Integer id) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = RequestUtil.getRequestToken(request);
        User user = authClient.getUserInfoByToken(token).getData();
        ShopDetailVO shopDetailVO = baseMapper.selectShopDetailById(id, user.getId());
        if(Optional.ofNullable(shopDetailVO).isPresent()){
            shopDetailVO.setSliderShow(Arrays.asList(shopDetailVO.getSliderShows().split(",")));
            shopDetailVO.setShopDescription(HtmlUtil.unescape(shopDetailVO.getShopDescription()));
            return shopDetailVO;
        }
        throw new NotFoundRecordException("商品不存在");
    }
}
