package com.alexduzi.expensetracking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alexduzi.expensetracking.domain.Budget;
import com.alexduzi.expensetracking.domain.Category;
import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateBudgetDTO;
import com.alexduzi.expensetracking.dto.request.UpdateBudgetDTO;
import com.alexduzi.expensetracking.dto.response.BudgetDTO;
import com.alexduzi.expensetracking.exception.DatabaseException;
import com.alexduzi.expensetracking.exception.EntityAlreadyExistsException;
import com.alexduzi.expensetracking.exception.EntityNotFoundException;
import com.alexduzi.expensetracking.mapper.BudgetMapper;
import com.alexduzi.expensetracking.repository.BudgetRepository;
import com.alexduzi.expensetracking.repository.CategoryRepository;
import com.alexduzi.expensetracking.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final BudgetMapper budgetMapper;

    public BudgetService(BudgetRepository budgetRepository, CategoryRepository categoryRepository, UserRepository userRepository, BudgetMapper budgetMapper) {
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.budgetMapper = budgetMapper;
    }

    public List<BudgetDTO> findAll() {
        return budgetMapper.toDto(budgetRepository.findAll());
    }

    @Transactional
    public BudgetDTO create(CreateBudgetDTO dto) {
        budgetRepository.findByDescriptionEqualsIgnoreCase(dto.description())
                .ifPresent(b -> {
                    throw new EntityAlreadyExistsException("Budget already exists");
                });

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Budget budget = budgetMapper.toEntity(dto);
        budget.setUser(user);
        budget.setCategory(category);

        try {
            return budgetMapper.toDto(budgetRepository.save(budget));
        } catch(Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public BudgetDTO update(Long id, UpdateBudgetDTO dto) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Budget with id %s not exists", id)));

        budgetMapper.updateEntityFromDto(dto, budget);

        try {
            return budgetMapper.toDto(budgetRepository.save(budget));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!budgetRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Budget with id %s not exists", id));
        }
        try {
            budgetRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
