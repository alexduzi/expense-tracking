package com.alexduzi.expensetracking.service;

import com.alexduzi.expensetracking.domain.Category;
import com.alexduzi.expensetracking.dto.request.CreateCategoryDTO;
import com.alexduzi.expensetracking.dto.request.UpdateCategoryDTO;
import com.alexduzi.expensetracking.dto.response.CategoryDTO;
import com.alexduzi.expensetracking.exception.DatabaseException;
import com.alexduzi.expensetracking.exception.EntityAlreadyExistsException;
import com.alexduzi.expensetracking.exception.EntityNotFoundException;
import com.alexduzi.expensetracking.mapper.CategoryMapper;
import com.alexduzi.expensetracking.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> findAll() {
        return categoryMapper.toDto(categoryRepository.findAll());
    }

    public CategoryDTO create(CreateCategoryDTO dto) {
        Optional<Category> catOpt = categoryRepository.findByNameEqualsIgnoreCase(dto.name());
        if (catOpt.isPresent()) {
            throw new EntityAlreadyExistsException(String.format("Category with name %s already exists", dto.name()));
        }

        Category category = categoryMapper.toCategory(dto);

        try {
            return categoryMapper.toDto(categoryRepository.save(category));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public CategoryDTO update(Long id, UpdateCategoryDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Category with id %s not exists", id)));

        categoryMapper.updateEntityFromDto(dto, category);

        try {
            return categoryMapper.toDto(categoryRepository.save(category));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Category with id %s not exists", id));
        }

        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
