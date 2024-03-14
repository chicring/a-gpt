package com.hjong.one.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiKey {
    private Integer id;
    private String name;
    private String apiKey;
    private Boolean enabled;
    private int userId;
    private Long createdAt;
    private Long expiresAt;
}




