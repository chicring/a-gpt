package com.hjong.one.adapter.openai;

import com.hjong.one.adapter.CompletionsAdapter;
import com.hjong.one.entity.DTO.Channel;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component("openai")
public class OpenAiCompletions extends CompletionsAdapter {

    @Override
    protected Flux<String> completions(OpenAiRequestBody request, Channel channel) {
        return super.webClient.post()
                .uri(channel.getBaseUrl())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + channel.getApiKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .flux();
    }


    @Override
    protected Flux<String> streamCompletions(OpenAiRequestBody request, Channel channel) {
        return super.webClient.post()
                .uri(channel.getBaseUrl())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + channel.getApiKey())
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(String.class);
    }
}
