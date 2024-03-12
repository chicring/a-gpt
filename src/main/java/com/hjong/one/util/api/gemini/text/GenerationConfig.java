package com.hjong.one.util.api.gemini.text;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class GenerationConfig {
    private double temperature;
    private int topK;
    private int topP;
    private int maxOutputTokens;
    private List<String> stopSequences;
}
