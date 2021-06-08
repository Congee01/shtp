package com.shtp.service;

import com.github.pagehelper.PageInfo;
import com.shtp.vo.ProductVO;
import com.shtp.vo.ResultVO;

import java.util.List;

public interface ProductService {
    // 根据关键字，分页查询课程
    PageInfo<ProductVO> getProducts(Integer currPage, Integer pageSize, Integer uid, String key);
    // 查询用户所有已购买的课程
    List<ProductVO> getBoughtProduct(Integer uid);
    // 查询用户所有可管理的课程
    List<ProductVO> getManageableProducts(Integer uid);
    // 查询单个商品
    ProductVO getProduct(Integer productId, Integer uid);
    // 创建课程
    ResultVO<ProductVO> createCourse(ProductVO productVOs);
}
