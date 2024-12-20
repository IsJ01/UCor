package com.pages.dto;

import java.util.List;

import com.pages.database.entity.Element;

import lombok.Value;

@Value
public class ElementReadDto {
    Long id;
    String tag;
    Long parentId;
    Long pageId;
    List<PropertiesReadDto> properties;
    List<Element> childrens;
}
