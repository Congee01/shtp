package com.shtp.vo;

import com.shtp.po.ProductOrder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class ProductOrderVO {
    private Integer id;

    private Integer cost;

    private Integer productId;

    private String productName;

    private Date createTime;

    private Integer userId;

    private Integer status;

    public ProductOrderVO() {
    }

    public ProductOrderVO(@NonNull ProductOrder order) {
        id = order.getId();
        cost = order.getCost();
        productId=order.getProductId();
        productName=order.getProductName();
        createTime = order.getCreateTime();
        userId = order.getUserId();
        status = order.getStatus();
    }

}
