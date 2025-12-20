package com.university.java_lecture_3.mapper;

import com.university.java_lecture_3.dto.response.EventRegistrationResponse;
import com.university.java_lecture_3.model.EventRegistration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, EventMapper.class})
public interface EventRegistrationMapper {

    EventRegistrationResponse toEventRegistrationResponse(EventRegistration eventRegistration);

    List<EventRegistrationResponse> toEventRegistrationResponseList(List<EventRegistration> eventRegistrations);

}
