package com.alexduzi.expensetracking.controller;

import com.alexduzi.expensetracking.dto.request.CreateTagRequest;
import com.alexduzi.expensetracking.dto.request.UpdateTagRequest;
import com.alexduzi.expensetracking.dto.response.TagResponse;
import com.alexduzi.expensetracking.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("")
    public ResponseEntity<List<TagResponse>> findAll() {
        return ResponseEntity.ok(tagService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<TagResponse> create(@RequestBody CreateTagRequest request) {
        TagResponse result = tagService.create(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TagResponse> update(@PathVariable Long id, @RequestBody UpdateTagRequest request) {
        TagResponse result = tagService.update(id, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
