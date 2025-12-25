package com.university.java_lecture_3.mapper;

import com.university.java_lecture_3.model.dto.request.CreateUserRequest;
import com.university.java_lecture_3.model.dto.response.EventResponse;
import com.university.java_lecture_3.model.dto.response.GroupResponse;
import com.university.java_lecture_3.model.dto.response.UserDetailedResponse;
import com.university.java_lecture_3.model.dto.response.UserSummaryResponse;
import com.university.java_lecture_3.model.entity.EventRegistration;
import com.university.java_lecture_3.model.entity.Group;
import com.university.java_lecture_3.model.entity.User;
import com.university.java_lecture_3.model.projection.UserSummaryProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final GroupMapper groupMapper;
    private final EventMapper eventMapper;

    public UserDetailedResponse toDetailedResponse(User entity) {
        List<EventResponse> events = entity.getEventRegistrations()
                .stream()
                .map(EventRegistration::getEvent)
                .map(eventMapper::toResponse)
                .toList();

        Group groupEntity = entity.getGroup();
        GroupResponse group = groupMapper.mapToResponse(groupEntity);

        return new UserDetailedResponse(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getEmail(),
                entity.getRole(),
                group,
                events
        );
    }

    public UserSummaryResponse toSummaryResponse(User entity) {
        return new UserSummaryResponse(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getEmail(),
                entity.getRole(),

                entity.getGroup() == null ? null : entity.getGroup().getName(),
                entity.getEventRegistrations().size()
        );
    }

    public User toEntity(CreateUserRequest request, Group group) {
        User entity = new User();
        entity.setName(request.getName());
        entity.setAge(request.getAge());
        entity.setEmail(request.getEmail());
        entity.setRole(request.getRole());
        entity.setGroup(group);
        entity.setEventRegistrations(new ArrayList<>());
        return entity;
    }

    public UserSummaryResponse toSummaryResponse(UserSummaryProjection projection) {
        return new UserSummaryResponse(
                projection.getId(),
                projection.getName(),
                projection.getAge(),
                projection.getEmail(),
                projection.getRole(),

                projection.getGroupName(),
                projection.getEventsCount()
        );
    }




}