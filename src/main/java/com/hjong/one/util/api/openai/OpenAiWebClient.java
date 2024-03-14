//package com.hjong.one.util.api.openai;
//
//import com.hjong.one.entity.DTO.Channel;
//import com.hjong.one.util.api.ApiAdapter;
//import com.hjong.one.util.api.openai.text.OpenAiReq;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//
//@Slf4j
//@Component("openai")
//public class OpenAiWebClient implements ApiAdapter {
//
//    @Resource
//    WebClient webClient;
//
//    @Override
//    public Flux<String> StreamResponse(Channel channel, OpenAiReq openAiReq) {
//
//
//        return webClient.post()
//                .uri(channel.getBaseUrl())
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + channel.getApiKey())
//                .bodyValue(openAiReq)
//                .retrieve()
//                .bodyToFlux(String.class);
////                .doOnNext(response -> log.info("回答: {}", response));
////                .onErrorResume(WebClientResponseException.class, ex -> {
////                    HttpStatus status = ex.getStatusCode();
////                    String res = ex.getResponseBodyAsString();
////                    log.error("OpenAI API error: {} {}", status, res);
////                    return Mono.error(new RuntimeException(res));
////                });
//    }
//}
