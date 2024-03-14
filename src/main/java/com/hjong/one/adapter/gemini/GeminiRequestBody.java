package com.hjong.one.adapter.gemini;


import lombok.Data;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/14
 **/
public class GeminiRequestBody {

    private List<Content> contents;
    private GenerationConfig generationConfig;
    @Data
    public static class Content {
        private String role;
        private List<Part> parts;
    }

    @Data
    public static class Part {
        private String text;
    }

    @Data
    public static class GenerationConfig {
        private double temperature;
        private int topK;
        private int topP;
        private int maxOutputTokens;
        private List<String> stopSequences;
    }
}
