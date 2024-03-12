package com.hjong.one.controller;


import com.hjong.one.entity.Channel;
import com.hjong.one.service.AuthService;
import com.hjong.one.service.ChannelService;
import com.hjong.one.util.api.ApiAdapter;
import com.hjong.one.util.api.openai.text.OpenAiReq;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1")
public class ApiController {

    @Resource
    private Map<String, ApiAdapter> selectorMap;
    @Resource
    ChannelService channelService;
    @Resource
    AuthService authService;

    @CrossOrigin
    @PostMapping(value = "/chat/completions",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamCompletions(@RequestBody OpenAiReq openAiReq, HttpServletRequest request){
        /*
          从数据库中验证apikey是否有效，如果有效查询key能用的渠道并返回对应的渠道信息，如果出错抛出异常
         */
//        log.info("用户输入：{}", openAiReq.getMessages().getFirst().getContent());
        log.info("来自ip：{} 的请求",request.getLocalAddr());
        if (authService.validateKey(request.getHeader("Authorization"))){
            Channel channel = channelService.selectChannel(openAiReq.getModel());

            return selectorMap.get(channel.getType()).getResponse(channel,openAiReq);

        }else {
            return null;
        }

    }


}
