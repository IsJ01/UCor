package com.pages.mapper;

import org.springframework.stereotype.Component;

import com.pages.database.entity.TextElement;
import com.pages.dto.TextCreateEditDto;

@Component
public class TextCreateEditMapper implements Mapper<TextCreateEditDto, TextElement> {

    @Override
    public TextElement map(TextCreateEditDto fromObject) {
        TextElement text = new TextElement();
        copy(fromObject, text);
        return text;
    }

    @Override
    public TextElement map(TextCreateEditDto fromObject, TextElement toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(TextCreateEditDto fromObject, TextElement toObject) {
        toObject.setPageId(fromObject.getPageId());
        toObject.setParentId(fromObject.getParentId());
        toObject.setValue(fromObject.getValue());
    }
}
