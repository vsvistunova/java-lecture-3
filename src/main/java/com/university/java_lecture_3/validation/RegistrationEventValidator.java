package com.university.java_lecture_3.validation;

import com.university.java_lecture_3.exception.EventValidationException;
import com.university.java_lecture_3.model.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegistrationEventValidator implements Validator<Event, EventValidationException> {

    private static final int NO_LIMIT = 0;

    @Override
    public void validate(Event event) throws EventValidationException {
        if (LocalDateTime.now().isAfter(event.getDateTime())) {
            throw new EventValidationException("Event is already passed");
        }
        if (event.getMaxNumOfPeople() != NO_LIMIT && event.getSignedUpUsers().size() >= event.getMaxNumOfPeople()) {
            throw new EventValidationException("The maximum number of participants in the event has been reached");
        }
    }

}
