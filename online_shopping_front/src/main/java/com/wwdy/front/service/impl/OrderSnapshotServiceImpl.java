package com.wwdy.front.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.front.mapper.OrderSnapshotMapper;
import com.wwdy.front.pojo.OrderSnapshot;
import com.wwdy.front.service.OrderSnapshotService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  wwdy
 * @date  2022/4/9 14:29
 */
@Service
public class OrderSnapshotServiceImpl extends ServiceImpl<OrderSnapshotMapper, OrderSnapshot> implements OrderSnapshotService{

    /**
     * 添加订单快照
     * @param orders 订单快照
     * @return int
     */
    @Override
    public boolean insertSnapshot(List<OrderSnapshot> orders) {
        return saveBatch(orders);
    }
}




