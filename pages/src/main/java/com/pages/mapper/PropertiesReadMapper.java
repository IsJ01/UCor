package com.pages.mapper;

import org.springframework.stereotype.Component;

import com.pages.database.entity.ElementProperties;
import com.pages.dto.PropertiesReadDto;

@Component
public class PropertiesReadMapper implements Mapper<ElementProperties, PropertiesReadDto> {

    @Override
    public PropertiesReadDto map(ElementProperties fromObject) {
        return new PropertiesReadDto(
            fromObject.getId(), 
            fromObject.getFunction(), 
            fromObject.getElementId()
        );
    }

}
