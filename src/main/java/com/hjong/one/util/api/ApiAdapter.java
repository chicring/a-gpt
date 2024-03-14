package com.hjong.one.util.api;

import com.hjong.one.entity.DTO.Channel;
import com.hjong.one.util.api.openai.text.OpenAiReq;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface ApiAdapter {
    Flux<String> StreamResponse(Channel channel, OpenAiReq openAiReq);


}
