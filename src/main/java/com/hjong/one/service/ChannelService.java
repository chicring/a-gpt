package com.hjong.one.service;

import com.hjong.one.entity.DTO.Channel;
import org.springframework.stereotype.Service;

@Service
public interface ChannelService {

    /**
     * 模型选择
     *
     */
    Channel selectChannel(String model);

    String addChannel(Channel channel);

    String updateChannelById(Channel channel);

    String deleteChannelById(String id);
}
