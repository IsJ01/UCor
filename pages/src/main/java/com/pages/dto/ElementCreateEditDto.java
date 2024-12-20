package com.pages.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ElementCreateEditDto {

    @NotNull
    String tag;

    @NotNull
    Long parentId;
    
    @NotNull
    Long pageId;
}
