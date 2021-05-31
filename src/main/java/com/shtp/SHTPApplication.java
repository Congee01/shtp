package com.shtp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.shtp.mapperservice")
public class SHTPApplication {
    public static void main(String[] args) {
        SpringApplication.run(SHTPApplication.class, args);
    }
}
