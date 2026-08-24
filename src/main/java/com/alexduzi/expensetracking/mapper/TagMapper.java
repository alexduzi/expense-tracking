package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.Tag;
import com.alexduzi.expensetracking.dto.request.CreateTagRequest;
import com.alexduzi.expensetracking.dto.request.UpdateTagRequest;
import com.alexduzi.expensetracking.dto.response.TagResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagResponse toDto(Tag entity);
    Tag toTag(CreateTagRequest dto);
    Tag toTag(UpdateTagRequest dto);
}
