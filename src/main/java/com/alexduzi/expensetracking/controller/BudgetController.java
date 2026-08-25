package com.alexduzi.expensetracking.controller;

import com.alexduzi.expensetracking.dto.request.CreateBudgetDTO;
import com.alexduzi.expensetracking.dto.request.CreateCategoryDTO;
import com.alexduzi.expensetracking.dto.request.UpdateBudgetDTO;
import com.alexduzi.expensetracking.dto.request.UpdateCategoryDTO;
import com.alexduzi.expensetracking.dto.response.BudgetDTO;
import com.alexduzi.expensetracking.dto.response.CategoryDTO;
import com.alexduzi.expensetracking.service.BudgetService;
import com.alexduzi.expensetracking.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}/budget")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping()
    public ResponseEntity<List<BudgetDTO>> findAll() {
        return ResponseEntity.ok(budgetService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<BudgetDTO> create(@RequestBody CreateBudgetDTO request) {
        BudgetDTO result = budgetService.create(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BudgetDTO> update(@PathVariable Long id, @RequestBody UpdateBudgetDTO request) {
        return ResponseEntity.ok(budgetService.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        budgetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
