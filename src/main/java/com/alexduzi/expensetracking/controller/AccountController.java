package com.alexduzi.expensetracking.controller;

import com.alexduzi.expensetracking.dto.request.CreateAccountDTO;
import com.alexduzi.expensetracking.dto.response.AccountDTO;
import com.alexduzi.expensetracking.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "${api.prefix}/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody CreateAccountDTO request) {
        AccountDTO result = accountService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{accountNumber}")
                .buildAndExpand(result.accountNumber())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }
}
