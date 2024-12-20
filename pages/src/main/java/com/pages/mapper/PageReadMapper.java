package com.pages.mapper;

import org.springframework.stereotype.Component;

import com.pages.database.entity.Page;
import com.pages.dto.PageReadDto;

@Component
public class PageReadMapper implements Mapper<Page, PageReadDto> {

    @Override
    public PageReadDto map(Page fromObject) {
        return new PageReadDto(
            fromObject.getId(), 
            fromObject.getUri(),
            fromObject.getChildren()
        );
    }



}
