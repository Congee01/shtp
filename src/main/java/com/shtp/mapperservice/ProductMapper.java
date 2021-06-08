package com.shtp.mapperservice;

import com.shtp.po.Product;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    List<Product> selectByType(String type);

    List<Product> queryAll(String key);

    List<Product> selectById(Integer userId);

}
