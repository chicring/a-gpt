package com.hjong.one.util.api.openai.text;

import lombok.Data;
import java.util.List;

@Data
public class OpenAiReq {
    private List<OpenAiMessage> messages;
    private boolean stream;
    private String model;
    private double temperature;
    private int presence_penalty;
    private int frequency_penalty;
    private int top_p;
}
