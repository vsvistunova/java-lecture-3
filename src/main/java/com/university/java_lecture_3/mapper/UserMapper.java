package com.university.java_lecture_3.mapper;

import com.university.java_lecture_3.dto.request.UserRequest;
import com.university.java_lecture_3.dto.response.UserDetailedResponse;
import com.university.java_lecture_3.dto.response.UserSummaryResponse;
import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.projection.UserSummaryProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {GroupMapper.class, EventMapper.class}
)
public interface UserMapper {

    @Mapping(target = "group", source = "group")
    @Mapping(target = "events", source = "eventRegistrations")
    UserDetailedResponse toDetailedResponse(User user);

    @Mapping(target = "groupName", source = "group.name")
    @Mapping(
            target = "eventsCount",
            expression = "java(user.getEventRegistrations() != null ? user.getEventRegistrations().size() : 0)"
    )
    UserSummaryResponse toSummaryResponse(User user);

    List<UserSummaryResponse> toSummaryResponseListFromProjection(List<UserSummaryProjection> projection);

    User toEntity(UserRequest userRequest);

}
