package com.university.java_lecture_3.mapper;

import com.university.java_lecture_3.model.dto.response.EventResponse;
import com.university.java_lecture_3.model.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapper {

    public EventResponse toResponse(Event entity) {
        return new EventResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getEventTime(),
                entity.getLocation(),
                entity.getRegistrations().size()
        );
    }
}
