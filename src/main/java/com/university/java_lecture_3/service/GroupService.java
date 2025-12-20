package com.university.java_lecture_3.service;

import com.university.java_lecture_3.dto.request.GroupRequest;
import com.university.java_lecture_3.dto.response.GroupResponse;
import com.university.java_lecture_3.exception.NotFoundException;
import com.university.java_lecture_3.mapper.GroupMapper;
import com.university.java_lecture_3.model.Group;
import com.university.java_lecture_3.projection.GroupProjection;
import com.university.java_lecture_3.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public Group getById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Group with id " + id + " not found"));
    }

    public List<GroupResponse> getAll(Pageable pageable) {
        List<GroupProjection> groups = groupRepository.findAllWithUserCount(pageable);
        return groupMapper.toResponseListFromProjections(groups);
    }

    public GroupResponse save(GroupRequest request) {
        Group group = groupMapper.toEntity(request);
        Group savedGroup = groupRepository.save(group);

        return groupMapper.toResponse(savedGroup);
    }

}
