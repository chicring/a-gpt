package com.hjong.one.mapper;

import com.hjong.one.entity.DTO.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/13
 **/

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO User(username, password, email) VALUES(#{username}, #{password}, #{email}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertUser(User user);

    @Delete("DELETE FROM User WHERE id = #{id}")
    Integer deleteUser(int id);


    @Update("UPDATE User SET username = #{username}, password = #{password}, email = #{email}, WHERE id = #{id}")
    void updateUser(User user);


    @Select("SELECT * FROM User WHERE id = #{id}")
    User findUserById(int id);

    @Select("SELECT * FROM Users")
    List<User> getAllUsers();

    @Select("SELECT * FROM User WHERE username = #{text} OR email = #{text}")
    User findUserByNameOrEmail(String text);

}
