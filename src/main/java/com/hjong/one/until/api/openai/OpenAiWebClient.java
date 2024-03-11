package com.hjong.one.until.api.openai;

import com.alibaba.fastjson2.JSONObject;
import com.hjong.one.entity.Channel;
import com.hjong.one.until.api.ApiAdapter;
import com.hjong.one.until.api.openai.text.OpenAiReq;
import com.hjong.one.until.api.openai.text.OpenAiResp;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Component("openai")
public class OpenAiWebClient implements ApiAdapter {

    @Resource
    WebClient webClient;

    @Override
    public Flux<String> getResponse(Channel channel, OpenAiReq openAiReq) {


        return webClient.post()
                .uri(channel.getBaseUrl())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + channel.getApiKey())
                .bodyValue(openAiReq)
                .retrieve()
                .bodyToFlux(String.class);
//                .doOnNext(response -> log.info("回答: {}", response));
//                .onErrorResume(WebClientResponseException.class, ex -> {
//                    HttpStatus status = ex.getStatusCode();
//                    String res = ex.getResponseBodyAsString();
//                    log.error("OpenAI API error: {} {}", status, res);
//                    return Mono.error(new RuntimeException(res));
//                });
    }
}
