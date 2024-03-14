package com.hjong.one.service.impl;

import com.hjong.one.entity.DTO.ApiKey;
import com.hjong.one.exception.ServiceException;
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
        ApiKey apiKey = apiKeyMapper.findByKey(ApiKey.substring("Bearer ".length()));
        if (apiKey != null){
            /*
              有效日期和启用判断
             */
            if(apiKey.getEnabled()){
                Instant instant = Instant.now();
                long timeStampSeconds = instant.getEpochSecond();
                if (apiKey.getExpiresAt() >= timeStampSeconds){
                 return true;
                }else {
                    throw new ServiceException("key未启用或者已过期");
                }
            }
            return true;
        }else {
            log.info("无效的key");
            throw new ServiceException("无效的key");
        }
    }

    @Override
    public String addKey(String name,int expDay) {

        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();

        Instant future = instant.plus(expDay, ChronoUnit.DAYS);
        long expiresStampSeconds = future.getEpochSecond();

        String key = keyUtil.generateKey();
        ApiKey apiKey = new ApiKey().setApiKey(key)
                .setName(name)
                .setCreatedAt(timeStampSeconds)
                .setExpiresAt(expiresStampSeconds);

        if(apiKeyMapper.insert(apiKey) > 0){
            return key;
        }else {
            throw new ServiceException("生成key失败，请稍后再试。");
        }

    }

    @Override
    public String deleteKey(String id) {
        if(apiKeyMapper.delete(id) > 0){
            return "删除成功";
        }
        return null;
    }

    @Override
    public String updateKey(ApiKey key){
        return null;
    }

}
