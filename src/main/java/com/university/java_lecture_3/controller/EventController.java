package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.dto.request.EventRequest;
import com.university.java_lecture_3.dto.response.EventResponse;
import com.university.java_lecture_3.service.EventService;
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
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(
        name = "Мероприятия (Events)",
        description = "API для управления событиями: создание, просмотр, обновление и удаление."
)
public class EventController {

    private final EventService eventService;

//    @GetMapping("/filtered")
//    public List<EventResponse> getFilteredEvents(
//            @RequestParam(required = false) Long userId,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
//            @RequestParam(required = false) String location,
//            @RequestParam(required = false) Boolean onlyWithFreePlaces,
//            @RequestParam(required = false) Boolean onlyUpcoming
//    ) {
//        return eventService.getFilteredEvents(userId, startDate, endDate, location, onlyWithFreePlaces, onlyUpcoming);
//    }

    @GetMapping("/{id}/relevance")
    public ResponseEntity<Boolean> isRelevant(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.isRelevant(id));
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(eventService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<EventResponse> create(@Valid @RequestBody EventRequest event) {
        EventResponse saved = eventService.save(event);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> update(@PathVariable Long id, @Valid @RequestBody EventRequest event) {
        eventService.update(id, event);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
