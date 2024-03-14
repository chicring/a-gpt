package com.hjong.one.service.impl;


import com.hjong.one.adapter.CompletionsAdapter;
import com.hjong.one.adapter.openai.OpenAiRequestBody;
import com.hjong.one.entity.DTO.Channel;
import com.hjong.one.service.ChannelService;
import com.hjong.one.util.api.ApiAdapter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProxyServiceImpl {

    @Resource
    private Map<String, CompletionsAdapter> selectorMap;
    @Resource
    ChannelService channelService;
    public Flux<String> completions(OpenAiRequestBody request){

        if (request.getMessages().isEmpty()){
            return Flux.just("");
        }

        //获取输入的问题
        String question = request.getMessages().getLast().getContent();
        log.info("输入: {}", question);

        //根据模型选择合适的渠道
        Channel channel = channelService.selectChannel(request.getModel());

        return selectorMap.get(channel.getType()).sendMessage(request,channel);
    }
}
