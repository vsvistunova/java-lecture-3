package com.university.java_lecture_3.controller;


import com.university.java_lecture_3.model.dto.request.CreateGroupRequest;
import com.university.java_lecture_3.model.dto.response.GroupResponse;
import com.university.java_lecture_3.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public GroupResponse createGroup(@RequestBody CreateGroupRequest request) {
        return groupService.createGroup(request);
    }

    @GetMapping
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }
}
