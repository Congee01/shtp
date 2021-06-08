package com.shtp.vo;

import com.shtp.po.Product;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class ProductVO {
    private Integer id;

    private String name;

    private String intro;

    private String picture;

    private Date createTime;

    private Integer cost;

    private Integer managerId;

    private String managerName;

    // 标记当前用户是否已购买解锁
    private boolean bought;

    // 标记当前用户是否有权限管理课程
    private boolean manageable;

    public ProductVO() {
        bought = false;
    }

    public ProductVO(@NonNull Product product){
        id = product.getId();
        name = product.getName();
        intro = product.getIntro();
        picture = product.getPicture();
        createTime = product.getCreateTime();
        cost = product.getCost();
        managerId=product.getManagerId();
        managerName=product.getManagerName();
        bought = false;
        manageable = false;
    }

    public ProductVO(@NonNull Product product, @NonNull boolean bought, @NonNull boolean manageable){
        id = product.getId();
        name = product.getName();
        intro = product.getIntro();
        picture = product.getPicture();
        createTime = product.getCreateTime();
        cost = product.getCost();
        managerId=product.getManagerId();
        managerName=product.getManagerName();
        this.bought = bought;
        this.manageable = manageable;
    }

}
