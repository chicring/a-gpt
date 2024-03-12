package com.hjong.one.controller;

import com.hjong.one.entity.ApiKey;
import com.hjong.one.entity.R;
import com.hjong.one.service.AuthService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/12
 **/

@Validated
@RestController
@RequestMapping("/key")
public class ApiKeyController {

    @Resource
    AuthService authService;

    @GetMapping("/add")
    public R<String> addKey(@Size(min = 3, max = 15, message = "名称应该在3-15个字符之间") String name,
                            @Min(value = 1, message = "最小过期时间不能低于24小时") int exp){

        return R.ok(authService.addKey(name,exp));
    }
    @PostMapping("/update")
    public R<Void> update(@RequestBody ApiKey key){

        if(authService.updateKey(key) > 0){
            return R.ok();
        }

        return null;
    }


}
