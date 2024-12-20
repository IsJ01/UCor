package com.pages.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pages.database.entity.Element;
import com.pages.dto.ElementReadDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ElementReadMapper implements Mapper<Element, ElementReadDto> {

    private final PropertiesReadMapper propertiesReadMapper;

    @Override
    public ElementReadDto map(Element fromObject) {
        return new ElementReadDto(
            fromObject.getId(),
            fromObject.getTag(),
            fromObject.getParentId(), 
            fromObject.getPageId(), 
            Optional.ofNullable(fromObject.getProperties())
            .map(props -> {
                return props.stream()
                .map(propertiesReadMapper::map)
                .toList();
            })
            .orElse(null),
            fromObject.getChildren()
        );
    }

}
