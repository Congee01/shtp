package com.shtp.po;

import com.shtp.vo.ProductVO;
import lombok.NonNull;

import java.util.Date;

public class Product {
    private Integer id;

    private String name;

    private String intro;

    private String picture;

    private Date createTime;

    private Date deleteTime;

    private Integer cost;

    private Integer managerId;

    private String managerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId=managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String ManagerName) {
        this.managerName=ManagerName;
    }

    public Product() {
    }

    public Product(@NonNull ProductVO productVO){
        this.id = productVO.getId();
        this.name =productVO.getName();
        this.intro =productVO.getIntro();
        this.picture = productVO.getPicture();
        this.createTime =productVO.getCreateTime();
        this.cost = productVO.getCost();
        this.managerId = productVO.getManagerId();
        this.managerName = productVO.getManagerName();
    }
}
