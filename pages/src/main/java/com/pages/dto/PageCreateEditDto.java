package com.pages.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class PageCreateEditDto {
    
    @NotNull
    String uri;

}
