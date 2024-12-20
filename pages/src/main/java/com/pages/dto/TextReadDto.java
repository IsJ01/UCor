package com.pages.dto;

import lombok.Value;

@Value
public class TextReadDto {
    Long id;
    Long parentId;
    Long pageId;
    String value;
}
