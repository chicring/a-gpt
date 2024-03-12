package com.hjong.one.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    boolean validateKey(String ApiKey);

    /**
     *
     * @param name 密钥名称
     * @param expiresAt 过期时间
     * @return 返回key
     */
    String addKey(String name,Long expiresAt);

    Integer deleteKey(String id);
}
