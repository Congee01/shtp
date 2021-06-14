package com.shtp.po;

import com.shtp.vo.ProductOrderVO;
import lombok.NonNull;

import java.util.Date;

public class ProductOrder {
    private Integer id;

    private Integer cost;

    private Integer productId;

    private String productName;

    private Date createTime;

    private Integer userId;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getProductId(){return productId;}

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ProductOrder() {
    }

    public ProductOrder(@NonNull ProductOrderVO orderVO){
        this.id = orderVO.getId();
        this.cost = orderVO.getCost();
        this.productId= orderVO.getProductId();
        this.productName = orderVO.getProductName();
        this.createTime = orderVO.getCreateTime()==null ? new Date():orderVO.getCreateTime();
        this.userId = orderVO.getUserId();
        this.status = orderVO.getStatus();
    }
}
