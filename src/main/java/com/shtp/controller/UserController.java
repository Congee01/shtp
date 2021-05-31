package com.shtp.controller;

import com.shtp.service.UserService;
import com.shtp.vo.RechargeOrderVO;
import com.shtp.vo.ResultVO;
import com.shtp.vo.UserFormVO;
import com.shtp.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResultVO<UserVO> register(@RequestBody UserVO user){
        return userService.userRegister(user);
    }

    @PostMapping("/login")
    public ResultVO<UserVO> login(@RequestBody UserFormVO userForm){
        return userService.userLogin(userForm.getPhone(), userForm.getPassword());
    }

    @GetMapping("/{uid}")
    public UserVO getUser(@PathVariable Integer uid){
        return userService.getUser(uid);
    }

    @PostMapping("/recharge")
    public ResultVO<UserVO> recharge(@RequestBody RechargeOrderVO rechargeOrderVO){
        return userService.rechargeAccount(rechargeOrderVO);
    }
}
