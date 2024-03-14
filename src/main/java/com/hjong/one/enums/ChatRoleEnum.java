package com.hjong.one.enums;

import lombok.Getter;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/14
 **/
public enum ChatRoleEnum {

    SYSTEM("system","系统"),
    USER("user","用户"),
    ASSISTANT("assistant","助手"),
    MODEL("model","模型")
    ;

    @Getter
    private final String role;

    @Getter
    private final String des;

    ChatRoleEnum(String role, String des){
        this.role = role;
        this.des = des;
    }

}
