package com.hjong.one.util.api.openai.text;

import lombok.Data;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Data
public class OpenAiChoices {
    String finish_reason;
    Integer index;
    OpenAiMessage message;
    OpenAiMessage delta;
}