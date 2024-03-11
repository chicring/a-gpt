package com.hjong.one.service;

import com.hjong.one.entity.Channel;
import org.springframework.stereotype.Service;

@Service
public interface ChannelService {

    /**
     * 模型选择
     *
     */
    public Channel selectChannel(String model);
}
