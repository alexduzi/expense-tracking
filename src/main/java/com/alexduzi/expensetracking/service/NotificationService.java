package com.alexduzi.expensetracking.service;

import com.alexduzi.expensetracking.domain.Notification;
import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateNotificationDTO;
import com.alexduzi.expensetracking.dto.request.UpdateNotificationDTO;
import com.alexduzi.expensetracking.dto.response.NotificationDTO;
import com.alexduzi.expensetracking.exception.DatabaseException;
import com.alexduzi.expensetracking.exception.EntityNotFoundException;
import com.alexduzi.expensetracking.mapper.NotificationMapper;
import com.alexduzi.expensetracking.repository.NotificationRepository;
import com.alexduzi.expensetracking.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.notificationMapper = notificationMapper;
    }

    public List<NotificationDTO> findAll() {
        return notificationMapper.toDto(notificationRepository.findAll());
    }

    public NotificationDTO create(CreateNotificationDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        try {
            Notification notification = notificationMapper.toEntity(dto);
            notification.setUser(user);
            return notificationMapper.toDto(notificationRepository.save(notification));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public NotificationDTO update(Long id, UpdateNotificationDTO dto) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found"));

        notificationMapper.updateEntityFromDto(dto, notification);

        try {
            return notificationMapper.toDto(notificationRepository.save(notification));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new EntityNotFoundException("Notification not found");
        }
        try {
            notificationRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<NotificationDTO> listByUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        return notificationMapper.toDto(notificationRepository.findNotificationsByUserId(id));
    }
}
