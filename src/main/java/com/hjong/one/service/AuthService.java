package com.hjong.one.service;

import com.hjong.one.entity.ApiKey;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {


    boolean validateKey(String ApiKey);

    /**
     *
     * @param name 密钥名称
     * @param expDay 过期天数
     * @return 返回key
     */
    String addKey(String name,int expDay);


    Integer deleteKey(String id);

    /**
     * 更新key包括：名称、启用状态、过期时间
     */
    Integer updateKey(ApiKey key);
}
