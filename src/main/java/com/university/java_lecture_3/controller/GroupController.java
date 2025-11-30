package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.dto.request.GroupRequest;
import com.university.java_lecture_3.dto.response.GroupResponse;
import com.university.java_lecture_3.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public List<GroupResponse> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return groupService.getAll(pageable);
    }

    @PostMapping
    public GroupResponse create(@Valid @RequestBody GroupRequest group) {
        return groupService.save(group);
    }

}
