package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.Budget;
import com.alexduzi.expensetracking.domain.Category;
import com.alexduzi.expensetracking.dto.request.CreateBudgetDTO;
import com.alexduzi.expensetracking.dto.request.CreateCategoryDTO;
import com.alexduzi.expensetracking.dto.request.UpdateBudgetDTO;
import com.alexduzi.expensetracking.dto.request.UpdateCategoryDTO;
import com.alexduzi.expensetracking.dto.response.BudgetDTO;
import com.alexduzi.expensetracking.dto.response.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BudgetMapper {
    BudgetMapper INSTANCE = Mappers.getMapper(BudgetMapper.class);

    BudgetDTO toDto(Budget entity);
    List<BudgetDTO> toDto(List<Budget> entity);
    Budget toEntity(CreateBudgetDTO dto);
    void updateEntityFromDto(UpdateBudgetDTO dto, @MappingTarget Budget entity);
}
