package com.hjong.one.until.api.openai.text;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class OpenAiMessage {
    private String role;
    private String content;
}
