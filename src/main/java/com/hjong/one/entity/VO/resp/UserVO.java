package com.hjong.one.entity.VO.resp;

import com.hjong.one.enums.RoleType;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/13
 **/

@Data
public class UserVO {
    private int id;
    private String username;
    private String email;
    private RoleType role;
    private int currentUsage;
    private int totalUsage;
    private Timestamp registerDate;
}
