package com.alexduzi.expensetracking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alexduzi.expensetracking.domain.Goal;
import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateGoalDTO;
import com.alexduzi.expensetracking.dto.request.UpdateGoalDTO;
import com.alexduzi.expensetracking.dto.response.GoalDTO;
import com.alexduzi.expensetracking.exception.DatabaseException;
import com.alexduzi.expensetracking.exception.EntityNotFoundException;
import com.alexduzi.expensetracking.mapper.GoalMapper;
import com.alexduzi.expensetracking.repository.GoalRepository;
import com.alexduzi.expensetracking.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class GoalService {
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;
    private final GoalMapper goalMapper;
    
    public GoalService(GoalRepository goalRepository, UserRepository userRepository, GoalMapper goalMapper) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
        this.goalMapper = goalMapper;
    }
    
    public List<GoalDTO> findAll() {
        return goalMapper.toDto(goalRepository.findAll());
    }
    
    public GoalDTO create(CreateGoalDTO dto) {
        User user = userRepository.findById(dto.userId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        Goal goal = goalMapper.toEntity(dto);
        goal.setUser(user);
        try {
            return goalMapper.toDto(goalRepository.save(goal));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    @Transactional
    public GoalDTO update(Long id, UpdateGoalDTO dto) {
        Goal goal = goalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Goal not found"));
        
        goalMapper.updateEntityFromDto(dto, goal);
        
        try {
            return goalMapper.toDto(goalRepository.save(goal));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    @Transactional
    public void delete(Long id) {
        if (!goalRepository.existsById(id)) {
            throw new EntityNotFoundException("Goal not found");
        }
        try {
            goalRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}