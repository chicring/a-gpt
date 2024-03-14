package com.hjong.one.controller;


import com.hjong.one.entity.DTO.Channel;
import com.hjong.one.entity.R;
import com.hjong.one.service.AuthService;
import com.hjong.one.service.ChannelService;
import com.hjong.one.util.api.ApiAdapter;
import com.hjong.one.util.api.openai.text.OpenAiReq;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Validated
@RestController
public class ApiController {

    @Resource
    private Map<String, ApiAdapter> selectorMap;
    @Resource
    ChannelService channelService;
    @Resource
    AuthService authService;

    @CrossOrigin
    @PostMapping(value = "/v1/chat/completions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<String> StreamCompletions(@RequestBody OpenAiReq openAiReq, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("用户输入：{}", openAiReq.getMessages().getLast().getContent());
        log.info("来自ip：{} 的请求",request.getLocalAddr());

        if (authService.validateKey(request.getHeader("Authorization"))){
            Channel channel = channelService.selectChannel(openAiReq.getModel());

            //选择流式输出和非流式输出
            if(openAiReq.isStream()){
                //返回 Flux<String>
                return selectorMap.get(channel.getType()).StreamResponse(channel,openAiReq);
            }else {
                // 返回一个空的 Flux<String>，以防止客户端收到 HTTP 500 错误
                return Mono.just("非流式响应");
            }
        }
            return Flux.empty();
    }

    @PostMapping("/no-stream")
    public R<String> Completions(){
        return R.ok("非流式输出");
    }
}
