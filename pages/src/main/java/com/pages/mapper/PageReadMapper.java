package com.pages.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pages.database.entity.Page;
import com.pages.dto.ElementReadDto;
import com.pages.dto.PageReadDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PageReadMapper implements Mapper<Page, PageReadDto> {

    private final ElementReadMapper elementReadMapper;

    @Override
    public PageReadDto map(Page fromObject) {
        List<ElementReadDto> elements = fromObject.getChildrens().stream()
            .map(elementReadMapper::map)
            .toList();

        return new PageReadDto(
            fromObject.getId(), 
            fromObject.getUri(), 
            elements
        );
    }

}
