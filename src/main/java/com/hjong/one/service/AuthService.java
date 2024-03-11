package com.hjong.one.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    boolean validateApiKey(String ApiKey);
}
