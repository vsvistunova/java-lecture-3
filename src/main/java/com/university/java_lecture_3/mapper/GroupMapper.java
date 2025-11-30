package com.university.java_lecture_3.mapper;

import com.university.java_lecture_3.dto.request.GroupRequest;
import com.university.java_lecture_3.dto.response.GroupResponse;
import com.university.java_lecture_3.model.Group;
import com.university.java_lecture_3.projection.GroupProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(GroupRequest groupRequest);

    @Mapping(target = "usersCount", expression = "java(group.getUsers() != null ? group.getUsers().size() : 0)")
    GroupResponse toResponse(Group group);

    List<GroupResponse> toResponseListFromProjections(List<GroupProjection> groups);

}
