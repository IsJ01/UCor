package com.pages.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class PropertiesCreateEditDto {

    String function;

    @NotNull
    Long elementId;
}
