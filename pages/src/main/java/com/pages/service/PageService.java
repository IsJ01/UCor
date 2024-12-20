package com.pages.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pages.database.repository.PageRepository;
import com.pages.dto.PageCreateEditDto;
import com.pages.dto.PageReadDto;
import com.pages.mapper.PageCreateEditMapper;
import com.pages.mapper.PageReadMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PageService {

    private final PageRepository pageRepository;
    private final PageReadMapper pageReadMapper;
    private final PageCreateEditMapper pageCreateEditMapper;

    public List<PageReadDto> findAll() {
        return pageRepository.findAll().stream()
            .map(pageReadMapper::map)
            .toList();
    }

    public Optional<PageReadDto> findById(Long id) {
        return pageRepository.findById(id)
            .map(pageReadMapper::map);
    }

    public Optional<PageReadDto> findByUri(String uri) {
        return pageRepository.findByUri(uri)
            .map(pageReadMapper::map);
    }

    @Transactional
    public PageReadDto create(PageCreateEditDto dto) {
        return Optional.of(dto)
            .map(pageCreateEditMapper::map)
            .map(pageRepository::save)
            .map(pageReadMapper::map)
            .orElseThrow();
    }

    @Transactional
    public Optional<PageReadDto> update(Long id, PageCreateEditDto dto) {
        return pageRepository.findById(id)
            .map(entity -> {
                return pageCreateEditMapper.map(dto, entity);
            })
            .map(pageRepository::saveAndFlush)
            .map(pageReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return pageRepository.findById(id)
            .map(entity -> {
                pageRepository.delete(entity);
                return true;
            })
            .orElse(false);
    }

}
