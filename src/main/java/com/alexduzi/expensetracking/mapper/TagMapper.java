package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.Tag;
import com.alexduzi.expensetracking.dto.request.CreateTagDTO;
import com.alexduzi.expensetracking.dto.request.UpdateTagDTO;
import com.alexduzi.expensetracking.dto.response.TagDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDTO toDto(Tag entity);
    Tag toTag(CreateTagDTO dto);
    Tag toTag(UpdateTagDTO dto);
}
