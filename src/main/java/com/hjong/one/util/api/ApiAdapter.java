package com.hjong.one.util.api;

import com.hjong.one.entity.Channel;
import com.hjong.one.util.api.openai.text.OpenAiReq;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface ApiAdapter {
    Flux<String> getResponse(Channel channel, OpenAiReq openAiReq);
}
