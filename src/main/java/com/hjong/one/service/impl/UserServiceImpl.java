package com.hjong.one.service.impl;

import com.hjong.one.entity.DTO.User;
import com.hjong.one.entity.VO.req.UserLoginVO;
import com.hjong.one.entity.VO.req.UserRegisterVO;
import com.hjong.one.entity.VO.resp.UserVO;
import com.hjong.one.exception.ServiceException;
import com.hjong.one.mapper.UserMapper;
import com.hjong.one.service.UserService;
import io.github.linpeilie.Converter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/13
 **/

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    private Converter converter;

    @Override
    public String updateUser(User user) {
        return null;
    }

    @Override
    public int findByByNameOrEmail(UserLoginVO vo) {
        User LoginUser = userMapper.findUserByNameOrEmail(vo.getAccount());
        if(LoginUser != null && LoginUser.getPassword().equals(vo.getPwd()))
            return LoginUser.getId();
        else
            throw new ServiceException("账号或密码错误");
    }

    @Override
    public UserVO findUserInfo(int id) {
        return converter.convert(userMapper.findUserById(id),UserVO.class);
    }

    @Override
    public String findAll() {
        return null;
    }

    @Override
    public String UserRegister(UserRegisterVO vo) {

        if(userMapper.insertUser(converter.convert(vo, User.class)) > 0)
            return "注册成功：" + vo.getUsername();
        else
            throw new ServiceException("注册失败");
    }
}
