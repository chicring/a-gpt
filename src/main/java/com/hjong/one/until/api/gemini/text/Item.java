package com.hjong.one.until.api.gemini.text;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class Item {
    private String role;
    private List<Part> parts;
}
