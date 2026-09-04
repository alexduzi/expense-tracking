package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.Notification;
import com.alexduzi.expensetracking.dto.request.CreateNotificationDTO;
import com.alexduzi.expensetracking.dto.request.UpdateNotificationDTO;
import com.alexduzi.expensetracking.dto.response.NotificationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mapping(source = "user.name", target = "userName")
    NotificationDTO toDto(Notification entity);
    List<NotificationDTO> toDto(List<Notification> entity);
    Notification toEntity(CreateNotificationDTO dto);
    void updateEntityFromDto(UpdateNotificationDTO dto, @MappingTarget Notification entity);
}
