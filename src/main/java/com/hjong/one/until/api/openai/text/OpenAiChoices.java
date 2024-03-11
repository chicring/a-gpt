package com.hjong.one.until.api.openai.text;

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