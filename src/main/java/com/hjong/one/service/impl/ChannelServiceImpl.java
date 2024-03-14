package com.hjong.one.service.impl;

import com.hjong.one.entity.DTO.Channel;
import com.hjong.one.mapper.ChannelMapper;
import com.hjong.one.service.ChannelService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Slf4j
@Service
public class ChannelServiceImpl implements ChannelService {

    Instant instant = Instant.now();
    long timeNow = instant.getEpochSecond();

    @Resource
    ChannelMapper channelMapper;

    @Override
    public Channel selectChannel(String model) {

        List<Channel> allChannel = channelMapper.findByModel(model);
        if(!allChannel.isEmpty()){
            String newModel = allChannel.getFirst().getModel();;
            return allChannel.getFirst().setModel(newModel.replace("\"", ""));
        }else {
            log.error("模型不可以用：{}",model);
            throw new RuntimeException("模型不可以用");
        }
    }

    @Override
    public String addChannel(Channel channel) {

        Integer isSuccess;
        try {
            if(channelMapper.insert(channel.setCreatedAt(timeNow)) > 0){
                return "添加成功";
            }else {
                return "添加失败";
            }
        } catch (Exception e) {
            log.info("添加渠道失败");
            throw new RuntimeException("添加渠道失败");
        }
    }

    @Override
    public String updateChannelById(Channel channel) {
        return null;
    }

    @Override
    public String deleteChannelById(String id) {
        return null;
    }

    
}
