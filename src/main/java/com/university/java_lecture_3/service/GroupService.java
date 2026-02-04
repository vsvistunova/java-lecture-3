package com.university.java_lecture_3.service;

import com.university.java_lecture_3.mapper.GroupMapper;
import com.university.java_lecture_3.model.dto.request.CreateGroupRequest;
import com.university.java_lecture_3.model.dto.response.GroupResponse;
import com.university.java_lecture_3.model.entity.Group;
import com.university.java_lecture_3.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupResponse createGroup(CreateGroupRequest request) {
        Group groupEntity = groupMapper.mapToEntity(request);
        Group savedEntity = groupRepository.save(groupEntity);
        return groupMapper.mapToResponse(savedEntity);

    }

    public List<GroupResponse> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(groupMapper::mapToResponse)
                .toList();
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }
}
