package com.shtp.controller;


import com.shtp.po.Product;
import com.shtp.service.ProductOrderService;
import com.shtp.vo.ProductOrderVO;
import com.shtp.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product_order")
public class ProductOrderController {
    @Resource
    private ProductOrderService productOrderService;


    @PostMapping("/create")
    public ResultVO<ProductOrderVO> createProductOrder(@RequestBody ProductOrderVO orderVO){
        return productOrderService.insertCourseOrder(orderVO);
    }

    @GetMapping("/uid/{uid}")
    public List<ProductOrderVO> getProductOrders(@PathVariable Integer uid){
        return productOrderService.getProductOrders(uid);
    }


    @PostMapping("/update")
    public ResultVO<ProductOrderVO> updateProductOrderStatus(@RequestParam Integer orderId, @RequestParam Integer orderStatus){
        return productOrderService.updateCourseOrder(orderId,orderStatus);
    }
}
