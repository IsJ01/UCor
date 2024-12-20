package com.pages.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pages.database.repository.TextElementRepository;
import com.pages.dto.TextCreateEditDto;
import com.pages.dto.TextReadDto;
import com.pages.mapper.TextCreateEditMapper;
import com.pages.mapper.TextReadMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TextElementService {

    private final TextElementRepository textRepository;
    private final TextReadMapper textReadMapper;
    private final TextCreateEditMapper textCreateEditMapper;

    public List<TextReadDto> findAll() {
        return textRepository.findAll().stream()
            .map(textReadMapper::map)
            .toList();
    }

    public Optional<TextReadDto> findById(Long id) {
        return textRepository.findById(id)
            .map(textReadMapper::map);
    }

    @Transactional
    public TextReadDto create(TextCreateEditDto dto) {
        return Optional.of(dto)
            .map(textCreateEditMapper::map)
            .map(textRepository::save)
            .map(textReadMapper::map)
            .orElseThrow();
    }

    @Transactional
    public Optional<TextReadDto> update(Long id, TextCreateEditDto dto) {
        return textRepository.findById(id)
            .map(entity -> {
                return textCreateEditMapper.map(dto, entity);
            })
            .map(textRepository::saveAndFlush)
            .map(textReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return textRepository.findById(id)
            .map(entity -> {
                textRepository.delete(entity);
                return true;
            })
            .orElse(false);
    }
}
