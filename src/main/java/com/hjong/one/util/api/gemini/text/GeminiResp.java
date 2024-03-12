package com.hjong.one.util.api.gemini.text;

import lombok.Data;

@Data
public class GeminiResp {
    private Content content;
    private String finishReason;
    private int index;
}
