package com.university.java_lecture_3.validation;

import com.university.java_lecture_3.exception.EventValidationException;
import com.university.java_lecture_3.model.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegistrationEventValidator implements Validator<Event, EventValidationException> {

    @Override
    public void validate(Event event) throws EventValidationException {
        if (LocalDateTime.now().isAfter(event.getDateTime())) {
            throw new EventValidationException("Event is already passed");
        }
        if (event.hasFreePlaces()) {
            throw new EventValidationException("The maximum number of participants in the event has been reached: %d/%d"
                    .formatted(event.getSignedUpUsers().size(), event.getMaxNumOfUsers()));
        }
    }

}
