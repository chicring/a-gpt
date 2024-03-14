package com.hjong.one.service;

import com.hjong.one.entity.DTO.User;
import com.hjong.one.entity.VO.req.UserLoginVO;
import com.hjong.one.entity.VO.req.UserRegisterVO;
import com.hjong.one.entity.VO.resp.UserVO;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/13
 **/
public interface UserService {
    String updateUser(User user);
    int findByByNameOrEmail(UserLoginVO vo);
    UserVO findUserInfo(int id);
    String findAll();
    String UserRegister(UserRegisterVO vo);
}
