package com.shtp.po;

import com.shtp.vo.UserVO;

import java.util.Date;

public class User {
    private Integer id;

    private String uname;

    private String phone;

    private String password;

    private String picture;

    private Integer balance;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User() {
    }

    public User(UserVO userVO){
        id = userVO.getId();
        uname = userVO.getUname();
        phone = userVO.getPhone();
        password = userVO.getPassword();
        picture = userVO.getPicture();
        balance = userVO.getBalance();
        createTime = userVO.getCreateTime();
    }
}