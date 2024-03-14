package com.hjong.one.entity.VO.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/13
 **/
@Data
public class UserLoginVO {
    @NotNull(message = "账号不能为空")
    String account;  //用户名或邮箱
    @NotNull(message = "密码不能为空")
    String pwd;
}
