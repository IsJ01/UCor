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

import com.pages.dto.TextCreateEditDto;
import com.pages.dto.TextReadDto;
import com.pages.service.TextElementService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RequiredArgsConstructor
@RequestMapping("/api/v1/texts")
public class TextElementController {

    private final TextElementService textElementService;

    @GetMapping("/")
    public List<TextReadDto> findAll() {
        return textElementService.findAll();
    }

    @GetMapping("/{id}")
    public TextReadDto findById(@PathVariable("id") Long id) {
        return textElementService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TextReadDto create(@Validated @RequestBody TextCreateEditDto dto) {
        return textElementService.create(dto);
    }

    @PutMapping("/{id}")
    public TextReadDto update(@PathVariable("id") Long id, 
        @Validated @RequestBody TextCreateEditDto dto) {
        return textElementService.update(id, dto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return textElementService.delete(id)
        ? noContent().build()
        : notFound().build();
    }
}