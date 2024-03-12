package com.hjong.one.controller;

import com.hjong.one.entity.R;
import com.hjong.one.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/12
 **/

@RestController
@RequestMapping("/key")
public class ApiKeyController {

    @Resource
    AuthService authService;

    @GetMapping("/add")
    public R<String> addKey(String name, Long exp){

        return R.ok(authService.addKey(name,exp));
    }
}
