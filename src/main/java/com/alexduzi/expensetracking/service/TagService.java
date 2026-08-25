package com.alexduzi.expensetracking.service;

import com.alexduzi.expensetracking.domain.Tag;
import com.alexduzi.expensetracking.dto.request.CreateTagDTO;
import com.alexduzi.expensetracking.dto.request.UpdateTagDTO;
import com.alexduzi.expensetracking.dto.response.TagDTO;
import com.alexduzi.expensetracking.exception.*;
import com.alexduzi.expensetracking.mapper.TagMapper;
import com.alexduzi.expensetracking.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public List<TagDTO> findAll() {
        return tagRepository.findAll().stream().map(TagMapper.INSTANCE::toDto).toList();
    }

    public TagDTO create(CreateTagDTO dto) {
        Optional<Tag> tag = tagRepository.findTagByNameEqualsIgnoreCase(dto.name());
        if (tag.isPresent()) {
            throw new EntityAlreadyExistsException("Tag already exists");
        }

        try {
            return tagMapper.toDto(tagRepository.save(tagMapper.toTag(dto)));
        } catch(Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public TagDTO update(Long id, UpdateTagDTO dto) {
        if (!tagRepository.existsById(id)) {
            throw new EntityNotFoundException("Tag not found");
        }

        Optional<Tag> tagOpt = tagRepository.findById(id);
        Tag tag = tagOpt.get();
        tag.setName(dto.name());

        try {
            return tagMapper.toDto(tagRepository.save(tag));
        } catch(Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void delete(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new EntityNotFoundException("Tag not found");
        }

        try {
            tagRepository.deleteById(id);
        } catch(Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
