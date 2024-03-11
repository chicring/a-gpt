package com.hjong.one.service.impl;

import com.hjong.one.entity.Channel;
import com.hjong.one.mapper.ChannelMapper;
import com.hjong.one.service.ChannelService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class ChannelServiceImpl implements ChannelService {

    @Resource
    ChannelMapper channelMapper;

    @Override
    public Channel selectChannel(String model) {

        List<Channel> allChannel = channelMapper.findAll();

        Optional<Channel> channelOptional = allChannel.stream()
                .filter(channel -> channel.getModels().contains(model))
                .findFirst();

        if(channelOptional.isPresent()) {
            log.info("渠道选择: {}, 站点: {}", channelOptional.get().getName(),channelOptional.get().getBaseUrl());
            channelOptional.get().setModels(model);
        } else {
            log.info("没有找到对应的渠道");
        }
        return channelOptional.orElse(null);
    }
}
