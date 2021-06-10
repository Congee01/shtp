package com.shtp.vo;

import lombok.Data;

@Data
public class ImgInfoVO {
    private String name;
    private String type;
    private String size;
    private String path;
    public ImgInfoVO(String path,String name, String type, String size) {
        this.path=path;
        this.name = name;
        this.type = type;
        this.size = size;
    }
}
