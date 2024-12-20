package com.pages.mapper;

import org.springframework.stereotype.Component;

import com.pages.database.entity.TextElement;
import com.pages.dto.TextReadDto;

@Component
public class TextReadMapper implements Mapper<TextElement, TextReadDto> {

    @Override
    public TextReadDto map(TextElement fromObject) {
        return new TextReadDto(
            fromObject.getId(), 
            fromObject.getParentId(), 
            fromObject.getPageId(), 
            fromObject.getValue()
        );
    }

}
