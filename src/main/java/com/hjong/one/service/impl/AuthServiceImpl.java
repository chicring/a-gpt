package com.hjong.one.service.impl;

import com.hjong.one.entity.Channel;
import com.hjong.one.mapper.ApiKeyMapper;
import com.hjong.one.service.AuthService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    ApiKeyMapper apiKeyMapper;

    @Override
    public boolean validateApiKey(String ApiKey) {
        if (apiKeyMapper.findByKey(ApiKey.substring("Bearer ".length())) != null){
            /*
              有效日期和启用判断
             */
            return true;
        }else {
            log.info("key不存在或无效");
            return false;
        }
    }
}
