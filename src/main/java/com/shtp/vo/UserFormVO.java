package com.shtp.vo;

import lombok.Data;

@Data
public class UserFormVO {
    private String phone;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
