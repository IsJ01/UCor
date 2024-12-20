package com.pages.mapper;

import org.springframework.stereotype.Component;

import com.pages.database.entity.ElementProperties;
import com.pages.dto.PropertiesCreateEditDto;

@Component
public class PropertiesCreateEditMapper implements Mapper<PropertiesCreateEditDto, ElementProperties> {

    @Override
    public ElementProperties map(PropertiesCreateEditDto fromObject) {
        ElementProperties properties = new ElementProperties();
        copy(fromObject, properties);
        return properties;
    }

    @Override
    public ElementProperties map(PropertiesCreateEditDto fromObject, ElementProperties toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(PropertiesCreateEditDto fromObject, ElementProperties toObject) {
        toObject.setFunction(fromObject.getFunction());
        toObject.setElementId(fromObject.getElementId());
    }

}
