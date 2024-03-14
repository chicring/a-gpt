package com.hjong.one.enums;

import lombok.Getter;

@Getter
public enum ChannelType {

    OPEN_AI("openai"),

    GEMINI("gemini");

    private final String type;

    ChannelType(String type){
        this.type = type;
    }
}
