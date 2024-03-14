package com.hjong.one.adapter.openai;

import lombok.Data;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/14
 **/

@Data
public class OpenAIResponseBody {
    String id;
    String object;
    String create;
    String model;
    List<Choices> choices;

    @Data
    public static class Choices{
        String finish_reason;
        Integer index;
        Message message;
        Message delta;
    }

    @Data
    public static class Message{
        private String role;
        private String content;
    }
}
