package com.hjong.one.entity;

import lombok.Data;

@Data
public class ApiKey {
    private Integer id;
    private String name;
    private String apiKey;
    private Boolean enabled;
    private Long createdAt;
    private Long expiresAt;
}




