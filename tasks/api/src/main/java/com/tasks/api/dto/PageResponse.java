package com.tasks.api.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Value;

@Value
public class PageResponse<T> {

    List<T> content;
    MetaData metaData;

    public static <T> PageResponse<T> of(Page<T> page) {
        var metaData = new MetaData(page.getNumber(), page.getSize(), page.getTotalElements());
        return new PageResponse<>(page.getContent(), metaData);
    }

    @Value
    public static class MetaData {
        int page;
        int size;
        long totalElements;
    }

}
