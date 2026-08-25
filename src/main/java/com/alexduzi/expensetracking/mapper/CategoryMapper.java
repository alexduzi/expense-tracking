package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.Category;
import com.alexduzi.expensetracking.dto.request.CreateCategoryDTO;
import com.alexduzi.expensetracking.dto.request.UpdateCategoryDTO;
import com.alexduzi.expensetracking.dto.response.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toDto(Category entity);
    List<CategoryDTO> toDto(List<Category> entity);
    Category toCategory(CreateCategoryDTO dto);
    void updateEntityFromDto(UpdateCategoryDTO dto, @MappingTarget Category entity);
}
