package com.chats.api.http.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.chats.api.dto.MessageCreateEditDto;
import com.chats.api.dto.MessageReadDto;
import com.chats.api.service.MessageService;

import lombok.RequiredArgsConstructor;

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


@RestController
@RequestMapping("/api/v1/messages")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/")
    public List<MessageReadDto> findAll() {
        return messageService.findAll();
    }

    @GetMapping("/{id}/")
    public MessageReadDto findById(@PathVariable Long id) {
        return messageService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageReadDto create(@Validated @RequestBody MessageCreateEditDto messageDto) {
        return messageService.create(messageDto);
    }

    @PutMapping("/{id}/")
    public MessageReadDto update(@PathVariable Long id, @Validated @RequestBody MessageCreateEditDto messageDto) {
        return messageService.update(id, messageDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return messageService.delete(id)
        ?
        noContent().build()
        :
        notFound().build();
    }

}