package com.pages.mapper;

import org.springframework.stereotype.Component;

import com.pages.database.entity.Element;
import com.pages.dto.ElementCreateEditDto;

@Component
public class ElementCreateEditMapper {

    public Element map(ElementCreateEditDto fromObject) {
        Element element = new Element();
        copy(fromObject, element);
        return element;
    }

    public Element map(ElementCreateEditDto fromObject, Element toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ElementCreateEditDto fromObject, Element toObject) {
        toObject.setTag(fromObject.getTag());
        toObject.setPageId(fromObject.getPageId());
        toObject.setParentId(fromObject.getParentId());
    }
}
