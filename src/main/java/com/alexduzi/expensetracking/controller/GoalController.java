package com.alexduzi.expensetracking.controller;

import com.alexduzi.expensetracking.repository.GoalRepository;
import java.net.URI;
import java.util.List;

import com.alexduzi.expensetracking.dto.request.CreateGoalDTO;
import com.alexduzi.expensetracking.dto.request.UpdateGoalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alexduzi.expensetracking.dto.response.GoalDTO;
import com.alexduzi.expensetracking.service.GoalService;


@RestController
@RequestMapping(path = "${api.prefix}/goal")
public class GoalController {
    private final GoalRepository goalRepository;
    private final GoalService goalService;
    
    public GoalController(GoalService goalService, GoalRepository goalRepository) {
        this.goalService = goalService;
        this.goalRepository = goalRepository;
    }
    
    @GetMapping()
    public ResponseEntity<List<GoalDTO>> findAll() {
        return ResponseEntity.ok(goalService.findAll());
    }
    
    @PostMapping("/create")
    public ResponseEntity<GoalDTO> postMethodName(@RequestBody CreateGoalDTO request) {
        GoalDTO result = goalService.create(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<GoalDTO> update(@PathVariable Long id, @RequestBody UpdateGoalDTO request) {
        return ResponseEntity.ok(goalService.update(id, request));
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        goalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
