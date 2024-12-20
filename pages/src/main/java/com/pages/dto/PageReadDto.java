package com.pages.dto;

import java.util.List;

import com.pages.database.entity.Element;

import lombok.Value;

@Value
public class PageReadDto {
    Long id;
    String uri;
    List<Element> childrens;
}
