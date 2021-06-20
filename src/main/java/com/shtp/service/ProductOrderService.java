package com.shtp.service;
import com.shtp.vo.ProductOrderVO;
import com.shtp.vo.ResultVO;

import java.util.List;

public interface ProductOrderService {
    // 创建订单
    ResultVO<ProductOrderVO> insertCourseOrder(ProductOrderVO orderVO);
    // 根据用户id获取该用户的所有订单
    List<ProductOrderVO> getProductOrders(Integer uid);
}
