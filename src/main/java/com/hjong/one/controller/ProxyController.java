package com.hjong.one.controller;


import com.hjong.one.adapter.openai.OpenAiRequestBody;
import com.hjong.one.entity.DTO.Channel;
import com.hjong.one.entity.R;
import com.hjong.one.service.AuthService;
import com.hjong.one.service.ChannelService;
import com.hjong.one.service.impl.ProxyServiceImpl;
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
public class ProxyController {

    @Resource
    private Map<String, ApiAdapter> selectorMap;
    @Resource
    ChannelService channelService;
    @Resource
    AuthService authService;

    @Resource
    ProxyServiceImpl proxyService;

    @CrossOrigin
    @PostMapping(value = "/v1/chat/completions")
    public Publisher<String> StreamCompletions(@RequestBody OpenAiRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("来自ip：{} 的请求",request.getLocalAddr());

        if (authService.validateKey(request.getHeader("Authorization"))){

            return proxyService.completions(requestBody);

        }
            return Flux.empty();
    }

    @PostMapping("/no-stream")
    public R<String> Completions(){
        return R.ok("非流式输出");
    }
}
