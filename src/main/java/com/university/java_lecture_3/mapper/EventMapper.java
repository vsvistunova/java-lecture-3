package com.university.java_lecture_3.mapper;

import com.university.java_lecture_3.dto.request.EventRequest;
import com.university.java_lecture_3.dto.response.EventResponse;
import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.model.EventRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventResponse toResponse(Event event);

    Event toEntity(EventRequest eventRequest);

    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "title", source = "event.title")
    @Mapping(target = "description", source = "event.description")
    @Mapping(target = "eventTime", source = "event.eventTime")
    @Mapping(target = "location", source = "event.location")
    @Mapping(target = "maxParticipants", source = "event.maxParticipants")
    EventResponse toResponse(EventRegistration eventRegistration);

    List<EventResponse> toResponseList(List<Event> events);

}
