package com.alexduzi.expensetracking.controller;

import com.alexduzi.expensetracking.dto.response.NotificationDTO;
import com.alexduzi.expensetracking.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<NotificationDTO>> listByUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notificationService.listByUser(id));
    }
}
