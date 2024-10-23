package com.chats.api.http.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.chats.api.dto.ChatCreateEditDto;
import com.chats.api.dto.ChatReadDto;

import com.chats.api.service.ChatService;

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
@RequestMapping("/api/v1/chats")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RequiredArgsConstructor
public class ChatRestController {

    private final ChatService chatService;
    
    @GetMapping("/")
    public List<ChatReadDto> findAll() {
        return chatService.findAll();
    }
    
    @GetMapping("/{id}/")
    public ChatReadDto findById(@PathVariable Long id) {
        return chatService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ChatReadDto create(@Validated @RequestBody ChatCreateEditDto chatDto) {
        return chatService.create(chatDto);
    }
    
    @PutMapping("/{id}/")
    public ChatReadDto update(@PathVariable Long id, @Validated @RequestBody ChatCreateEditDto chatDto) {
        return chatService.update(id, chatDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return chatService.delete(id)
        ?
        noContent().build()
        :
        notFound().build();
    }

}
