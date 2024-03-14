package com.hjong.one.adapter;

import com.hjong.one.adapter.openai.OpenAiRequestBody;
import com.hjong.one.entity.DTO.Channel;
import com.hjong.one.enums.ChannelType;
import com.hjong.one.enums.ChatRoleEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/14
 **/
@Slf4j
public abstract class CompletionsAdapter {

    @Resource
    public WebClient webClient;

    protected abstract Flux<String> completions(OpenAiRequestBody request, Channel channel);

    protected abstract Flux<String> streamCompletions(OpenAiRequestBody request, Channel channel);

    public Flux<String> sendMessage(OpenAiRequestBody request, Channel channel){

        //设置渠道选择的模型
        request.setModel(channel.getModel());

        //判断是否是openai的type, 非openai接口需要将System改成User
        if(!channel.getType().equals(ChannelType.OPEN_AI.getType())){
            List<OpenAiRequestBody.Message> messages = new ArrayList<>();

            request.getMessages().forEach( message -> {
                if(message.getRole().equals(ChatRoleEnum.SYSTEM.getRole())){
                    messages.add(message.builder(ChatRoleEnum.USER.getRole(), message.getContent()));
                    messages.add(message.builder(ChatRoleEnum.ASSISTANT.getRole(), message.getContent()));
                }
            });

            request.setMessages(messages);
        }

        Flux<String> flux;

        //选择是否启用流式响应
        if(request.isStream()){
            flux = streamCompletions(request,channel);
            return flux;
        }else {
            return completions(request,channel);
        }

    }

}
