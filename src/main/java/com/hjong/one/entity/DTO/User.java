package com.hjong.one.entity.DTO;

import com.hjong.one.entity.VO.req.UserRegisterVO;
import com.hjong.one.entity.VO.resp.UserVO;
import com.hjong.one.enums.RoleType;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/13
 **/

@Data
@AutoMappers({
        @AutoMapper(target = UserVO.class),
        @AutoMapper(target = UserRegisterVO.class)
})
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private RoleType role;
    private int currentUsage;
    private int totalUsage;
    private boolean isActive;
    private Timestamp registerDate;
}
