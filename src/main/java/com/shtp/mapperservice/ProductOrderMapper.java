package com.shtp.mapperservice;

import com.shtp.po.ProductOrder;

import java.util.List;

public interface ProductOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductOrder record);

    ProductOrder selectByPrimaryKey(Integer id);

    List<ProductOrder> selectAll();

    int updateByPrimaryKey(ProductOrder record);

    List<ProductOrder> selectByUserId(Integer userId);

    ProductOrder queryMostRecentOrder(Integer userId, Integer productId);

}
