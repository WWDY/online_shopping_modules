package com.wwdy.front.service;

import com.wwdy.front.pojo.OrderSnapshot;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author  wwdy
 * @date  2022/4/9 14:28
 */
public interface OrderSnapshotService extends IService<OrderSnapshot> {

    /**
     * 添加订单快照
     * @param orders 订单快照
     * @return int
     */
    boolean insertSnapshot(List<OrderSnapshot> orders);
}
