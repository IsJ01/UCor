package com.pages.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class TextCreateEditDto {
    
    @NotNull
    Long parentId;

    @NotNull
    Long pageId;
    
    String value;
}
