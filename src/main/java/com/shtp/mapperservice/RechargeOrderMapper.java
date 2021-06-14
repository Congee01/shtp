package com.shtp.mapperservice;

import com.shtp.po.RechargeOrder;

import java.util.List;

public interface RechargeOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(RechargeOrder record);

    RechargeOrder selectByPrimaryKey(Integer orderId);

    List<RechargeOrder> selectAll();

    int updateByPrimaryKey(RechargeOrder record);
}