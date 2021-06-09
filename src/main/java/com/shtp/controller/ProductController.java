package com.shtp.controller;

import com.github.pagehelper.PageInfo;
import com.shtp.service.ProductService;
import com.shtp.util.Constant;
import com.shtp.vo.ProductVO;
import com.shtp.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;


    @GetMapping("/{productId}")
    public ProductVO getProductById(@RequestParam(required = false, defaultValue = "") Integer uid, @PathVariable Integer productId) {
        return productService.getProduct(productId,uid);
    }

    @GetMapping("/all/{page}")
    public PageInfo<ProductVO> getProductsByKey(@RequestParam(required = false, defaultValue = "") Integer uid,
                                              @RequestParam(required = false, defaultValue = "") String key,
                                              @PathVariable Integer page) {
        return productService.getProducts(page, Constant.COURSE_PAGE_SIZE, uid, key);
    }


    @GetMapping("/uid/{uid}")
    public List<ProductVO> getBoughtProducts(@PathVariable Integer uid) {
        return productService.getBoughtProduct(uid);
    }


    @GetMapping("/uid/manage/{uid}")
    public List<ProductVO> getManageableProducts(@PathVariable Integer uid) {
        return productService.getManageableProducts(uid);
    }


    @PostMapping("/create")
    public ResultVO<ProductVO> createProduct(@RequestBody ProductVO productVO) {
        return productService.createCourse(productVO);
    }

    @PostMapping("/update")
    public ResultVO<ProductVO> updateProduct(@RequestBody ProductVO productVO){
        return productService.updateProduct(productVO);
    }
}
