package com.hjong.one.util.api.openai.text;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class OpenAiResp {
    String id;
    String object;
    String create;
    String model;
    List<OpenAiChoices> choices;

    public String asJsonString() {
        return JSONObject.toJSONString(this);
    }

}
