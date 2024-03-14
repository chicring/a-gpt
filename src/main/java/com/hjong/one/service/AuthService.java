package com.hjong.one.service;

import com.hjong.one.entity.DTO.ApiKey;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {


    boolean validateKey(String ApiKey);

    String addKey(String name,int expDay);

    String deleteKey(String id);

    String updateKey(ApiKey key);
}
