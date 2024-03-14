package com.hjong.one.util.api.gemini;


import com.alibaba.fastjson2.JSONObject;
import com.hjong.one.entity.DTO.Channel;
import com.hjong.one.util.api.ApiAdapter;
import com.hjong.one.util.api.gemini.text.GeminiReq;
import com.hjong.one.util.api.gemini.text.GenerationConfig;
import com.hjong.one.util.api.gemini.text.Item;
import com.hjong.one.util.api.gemini.text.Part;
import com.hjong.one.util.api.openai.text.OpenAiChoices;
import com.hjong.one.util.api.openai.text.OpenAiMessage;
import com.hjong.one.util.api.openai.text.OpenAiReq;
import com.hjong.one.util.api.openai.text.OpenAiResp;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component("gemini")
public class GeminiWebClient implements ApiAdapter {
    @Resource
    WebClient webClient;

    @Override
    public Flux<String> StreamResponse(Channel channel, OpenAiReq openAiReq) {

        String url = UriComponentsBuilder.fromHttpUrl(channel.getBaseUrl())
                .path("/v1beta/models/"+ channel.getModel() +":streamGenerateContent")
                .queryParam("key", channel.getApiKey())
                .toUriString();

//        String url = UriComponentsBuilder.fromHttpUrl(channel.getBaseUrl())
//                .path("/v1beta/models/"+ "gemini-1.0-pro-001" +":streamGenerateContent")
//                .queryParam("key", channel.getApiKey())
//                .toUriString();
//        HttpClient httpClient = HttpClient.create().proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP).host("127.0.0.1").port(20171));

        return webClient.post()
                .uri(url)
                .bodyValue(OpenAiToGemini(openAiReq).asJsonString())
                .retrieve()
                .bodyToFlux(String.class)
                .filter((str) -> str.contains("text"))
                .map(this::GeminiToOpenAi)
                .concatWithValues("[DONE]");
    }

    private GeminiReq OpenAiToGemini(OpenAiReq openAiReq){
        List<Item> contentsList = new ArrayList<>();

        int messagesCount = openAiReq.getMessages().size();
        String lastRole = openAiReq.getMessages().getFirst().getRole();

        for(int i = 0; i < messagesCount; i++) {
            OpenAiMessage message = openAiReq.getMessages().get(i);

            if(message.getRole().equals("assistant")){
                message.setRole("model");
            }
            if(message.getRole().equals("system")){

                message.setRole("user");

                if(lastRole.equals("user")){
                    Item newItem = new Item().setRole("model");
                    newItem.setParts(List.of(new Part("好的")));
                    contentsList.add(newItem);
                }

                lastRole = "user";

                Item item = new Item().setRole(message.getRole());
                item.setParts(List.of(new Part(message.getContent())));
                contentsList.add(item);
            }else {

                if(lastRole.equals(message.getRole()) && i!=0){
                    Item newItem = new Item().setRole("model");
                    newItem.setParts(List.of(new Part("好的")));
                    contentsList.add(newItem);
                }

                lastRole = message.getRole();

                Item item = new Item().setRole(message.getRole());
                item.setParts(List.of(new Part(message.getContent())));
                contentsList.add(item);
            }
        }

        return new GeminiReq().setContents(contentsList)
                .setGenerationConfig(
                        new GenerationConfig().setTemperature(openAiReq.getTemperature())
                                .setTopP(openAiReq.getTop_p())
                                .setTopK(1)
                                .setMaxOutputTokens(2048)
                                .setStopSequences(new ArrayList<>())
                );
    }


    private String GeminiToOpenAi(String text){
        JSONObject replyJson = JSONObject.parseObject("{" + text + "}");
        return new OpenAiResp().setChoices(
                    List.of(new OpenAiChoices().setMessage(
                            new OpenAiMessage().setContent(replyJson.getString("text"))
                                    .setRole("assistant")
                    ).setDelta(new OpenAiMessage().setContent(replyJson.getString("text"))
                            .setRole("assistant"))
                    )
                )
                .asJsonString();
    }
}
