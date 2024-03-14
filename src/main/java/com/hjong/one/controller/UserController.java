package com.hjong.one.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.hjong.one.entity.R;
import com.hjong.one.entity.VO.req.UserLoginVO;
import com.hjong.one.entity.VO.req.UserRegisterVO;
import com.hjong.one.entity.VO.resp.UserVO;
import com.hjong.one.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/13
 **/

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public R<SaTokenInfo> doLogin(@Valid @RequestBody UserLoginVO vo){
        StpUtil.login(userService.findByByNameOrEmail(vo));
        SaTokenInfo token = StpUtil.getTokenInfo();
        return R.ok("登录成功", token);
    }

    @PostMapping("/register")
    public R<String> doRegister(@Valid @RequestBody UserRegisterVO vo){
        return R.ok(userService.UserRegister(vo));
    }

    @SaCheckLogin
    @GetMapping("/info")
    public R<UserVO> userInfo(){
        return R.ok("查询成功", userService.findUserInfo(StpUtil.getLoginIdAsInt()));
    }

    @SaCheckLogin
    @RequestMapping("/logout")
    public R<String> logout() {
        StpUtil.logout();
        return R.ok("注销成功");
    }

}
