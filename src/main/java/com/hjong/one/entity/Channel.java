package com.hjong.one.entity;

import lombok.Data;

@Data
public class Channel {
    private Integer id;
    private String name;
    private Boolean enabled;
    private String type;
    private String apiKey;
    private String baseUrl;
    private String models;
    private Integer priority;
    private Integer usage;
    private String proxyIp;
    private Integer proxyPort;
    private Long createdAt;
}
