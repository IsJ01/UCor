package com.pages.http.rest.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pages.dto.PropertiesCreateEditDto;
import com.pages.dto.PropertiesReadDto;
import com.pages.service.PropertiesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RequestMapping("/api/v1/properties")
public class PropertiesController {

    private final PropertiesService propertiesService;

    @GetMapping("/")
    public List<PropertiesReadDto> findAll() {
        return propertiesService.findAll();
    }

    @GetMapping("/{id}")
    public PropertiesReadDto findById(@PathVariable("id") Long id) {
        return propertiesService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public PropertiesReadDto create(@Validated @RequestBody PropertiesCreateEditDto dto) {
        return propertiesService.create(dto);
    }

    @PutMapping("/{id}")
    public PropertiesReadDto update(@PathVariable("id") Long id, 
        @Validated @RequestBody PropertiesCreateEditDto dto) {
        return propertiesService.update(id, dto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return propertiesService.delete(id)
        ? noContent().build()
        : notFound().build();
    }
}
