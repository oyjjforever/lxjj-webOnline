package com.lxjj.wo.service.Impl;

import com.lxjj.wo.dao.OrderMainMapper;
import com.lxjj.wo.entity.Order;
import com.lxjj.wo.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ouyangjunjie
 * @version 1.0.0
 * @ClassName StatisticService.java
 * @Description TODO
 * @createTime 2019/09/27
 */
@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private OrderMainMapper orderMainMapper;
    @Override
    public Integer setReportOfMonth() {
        //获取 月订单
        List<Order> orders = orderMainMapper.query();
        //统计 销售额
        //统计 成本
        //统计 利润
        //统计 商品数量
        //统计 销售topN
        return null;
    }
    @Override
    public Integer setReportOfYear() {
        return null;
    }
}
