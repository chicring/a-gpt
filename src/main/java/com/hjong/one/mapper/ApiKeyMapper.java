package com.hjong.one.mapper;

import com.hjong.one.entity.DTO.ApiKey;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ApiKeyMapper {
    @Insert("INSERT INTO ApiKey(name, api_key, created_at, expires_at) VALUES(#{name}, #{apiKey}, #{createdAt}, #{expiresAt})")
    Integer insert(ApiKey apiKey);
    @Select("SELECT * FROM ApiKey WHERE  api_key =#{api_key}")
    ApiKey findByKey(String api_key);
    @Update("UPDATE ApiKey SET name=#{name}, enabled=#{enabled}, expires_at=#{expires_at} WHERE id=#{id}")
    void update(ApiKey apiKey);
    @Delete("DELETE FROM ApiKey WHERE id =#{id}")
    Integer delete(@Param("id") String id);

}
