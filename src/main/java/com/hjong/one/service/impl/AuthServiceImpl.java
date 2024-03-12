package com.hjong.one.service.impl;

import com.hjong.one.entity.ApiKey;
import com.hjong.one.mapper.ApiKeyMapper;
import com.hjong.one.service.AuthService;
import com.hjong.one.util.key.KeyUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    ApiKeyMapper apiKeyMapper;
    @Resource
    KeyUtil keyUtil;

    @Override
    public boolean validateKey(String ApiKey) {
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

    @Override
    public String addKey(String name,Long expiresAt) {

        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();

        Instant future = instant.plus(99 * 365, ChronoUnit.DAYS);
        long expiresStampSeconds = future.getEpochSecond();

        String key = keyUtil.generateKey();
        ApiKey apiKey = new ApiKey().setApiKey(key)
                .setName(name)
                .setCreatedAt(timeStampSeconds)
                .setExpiresAt(expiresStampSeconds);

        if(apiKeyMapper.insert(apiKey) > 0){
            return key;
        }else {
            throw new RuntimeException("生成key失败，请稍后再试。");
        }

    }

    @Override
    public Integer deleteKey(String id) {
        return null;
    }

}
