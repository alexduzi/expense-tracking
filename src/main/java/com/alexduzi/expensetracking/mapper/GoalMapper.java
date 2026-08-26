package com.alexduzi.expensetracking.mapper;

import java.util.List;

import com.alexduzi.expensetracking.dto.request.CreateGoalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.alexduzi.expensetracking.domain.Goal;
import com.alexduzi.expensetracking.dto.request.UpdateGoalDTO;
import com.alexduzi.expensetracking.dto.response.GoalDTO;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface GoalMapper {
    GoalMapper INSTANCE = Mappers.getMapper(GoalMapper.class);
    
    GoalDTO toDto(Goal entity);
    List<GoalDTO> toDto(List<Goal> entity);
    Goal toEntity(CreateGoalDTO dto);
    void updateEntityFromDto(UpdateGoalDTO dto, @MappingTarget Goal entity);
}
