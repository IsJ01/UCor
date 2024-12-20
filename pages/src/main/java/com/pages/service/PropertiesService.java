package com.pages.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pages.database.repository.ElementPropertiesRepository;
import com.pages.dto.PropertiesCreateEditDto;
import com.pages.dto.PropertiesReadDto;
import com.pages.mapper.PropertiesCreateEditMapper;
import com.pages.mapper.PropertiesReadMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PropertiesService {
    private final ElementPropertiesRepository elementPropertiesRepository;
    private final PropertiesReadMapper propertiesReadMapper;
    private final PropertiesCreateEditMapper propertiesCreateEditMapper;

    public List<PropertiesReadDto> findAll() {
        return elementPropertiesRepository.findAll().stream()
            .map(propertiesReadMapper::map)
            .toList();
    }

    public Optional<PropertiesReadDto> findById(Long id) {
        return elementPropertiesRepository.findById(id)
            .map(propertiesReadMapper::map);
    }

    @Transactional
    public PropertiesReadDto create(PropertiesCreateEditDto dto) {
        return Optional.of(dto)
            .map(propertiesCreateEditMapper::map)
            .map(elementPropertiesRepository::save)
            .map(propertiesReadMapper::map)
            .orElseThrow();
    }

    @Transactional
    public Optional<PropertiesReadDto> update(Long id, PropertiesCreateEditDto dto) {
        return elementPropertiesRepository.findById(id)
            .map(entity -> {
                return propertiesCreateEditMapper.map(dto, entity);
            })
            .map(elementPropertiesRepository::saveAndFlush)
            .map(propertiesReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return elementPropertiesRepository.findById(id)
            .map(entity -> {
                elementPropertiesRepository.delete(entity);
                return true;
            })
            .orElse(false);
    }
}
