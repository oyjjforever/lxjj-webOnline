package com.lxjj.wo.dao;

import com.lxjj.wo.entity.Order;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMainMapper extends Mapper<Order> {
    List<Order> query();
}
