package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.dto.request.GroupRequest;
import com.university.java_lecture_3.dto.response.GroupResponse;
import com.university.java_lecture_3.service.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@Tag(
        name = "Группы (Groups)",
        description = "API для управления группами пользователей."
)
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupResponse>> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(groupService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<GroupResponse> create(@Valid @RequestBody GroupRequest group) {
        return new ResponseEntity<>(groupService.save(group), HttpStatus.CREATED);
    }

}
