package com.hjong.one.mapper;

import com.hjong.one.entity.DTO.Channel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChannelMapper {

    @Insert("INSERT INTO Channel(name, type, api_key, base_url, models, priority, usage, proxy_ip, proxy_port, created_at) VALUES(#{name}, #{type}, #{apiKey}, #{baseUrl}, #{models}, #{priority}, #{usage}, #{proxyIp}, #{proxyPort}, #{createdAt})")
    Integer insert(Channel channel);
    @Select("SELECT * FROM Channel WHERE id = #{id}")
    Channel findById(@Param("id") Integer id);
    @Update("UPDATE Channel SET name=#{name}, enabled=#{enabled}, type=#{type}, api_key=#{apiKey}, base_url=#{baseUrl}, models=#{models}, priority=#{priority}, usage=#{usage}, proxy_ip=#{proxyIp}, proxy_port=#{proxyPort}, created_at=#{createdAt} WHERE id=#{id}")
    Integer update(Channel channel);
    @Delete("DELETE FROM Channel WHERE id =#{id}")
    Integer delete(@Param("id") Integer id);

    @Select("SELECT  * FROM Channel")
    List<Channel> findAll();

    @Select("SELECT * , JSON_EXTRACT(models, '$.${model}') as model FROM Channel HAVING model IS NOT NULL")
    List<Channel> findByModel(String model);
}
