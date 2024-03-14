package com.hjong.one.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Channel {
    private Integer id;
    private String name;
    private Boolean enabled;
    private String type;
    private String apiKey;
    private String baseUrl;
    private String models;
    private String model;
    private Integer priority;
    private Integer usage;
    private String proxyIp;
    private Integer proxyPort;
    private Long createdAt;
}
