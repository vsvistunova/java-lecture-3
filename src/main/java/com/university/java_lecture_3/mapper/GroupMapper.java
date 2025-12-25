package com.university.java_lecture_3.mapper;

import com.university.java_lecture_3.model.dto.request.CreateGroupRequest;
import com.university.java_lecture_3.model.dto.response.GroupResponse;
import com.university.java_lecture_3.model.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class GroupMapper {

    public GroupResponse mapToResponse(Group groupEntity) {
        return groupEntity == null
                ? null
                : new GroupResponse(
                groupEntity.getId(),
                groupEntity.getName(),
                groupEntity.getCuratorName(),
                groupEntity.getUsers().size()
        );
    }

    public Group mapToEntity(CreateGroupRequest request) {
        if (request == null) {
            return null;
        }
        Group group = new Group();
        group.setName(request.getName());
        group.setCuratorName(request.getCuratorName());
        group.setUsers(new ArrayList<>());
        return group;
    }
}
