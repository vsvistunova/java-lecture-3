package com.university.java_lecture_3.validation;

import com.university.java_lecture_3.exception.EventValidationException;
import com.university.java_lecture_3.model.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreationEventValidator implements Validator<Event, EventValidationException> {

    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 64;
    private static final int DESCRIPTION_MAX_LENGTH = 1024;
    private static final int LOCATION_MIN_LENGTH = 3;
    private static final int LOCATION_MAX_LENGTH = 128;
    private static final int MIN_NUM_OF_USERS = 0;

    @Override
    public void validate(Event event) throws EventValidationException {
        checkNullParams(event);

        int nameLength = event.getName().length();
        if (nameLength < NAME_MIN_LENGTH || nameLength > NAME_MAX_LENGTH) {
            throw new EventValidationException("Event name should be between %d and %d characters"
                    .formatted(NAME_MIN_LENGTH, NAME_MAX_LENGTH));
        }

        String description = event.getDescription();
        if (description != null && description.length() > DESCRIPTION_MAX_LENGTH) {
            throw new EventValidationException("Event description should be less than %d characters"
                    .formatted(DESCRIPTION_MAX_LENGTH));
        }

        if (event.getMaxNumOfUsers() < MIN_NUM_OF_USERS) {
            throw new EventValidationException("Max number of users should be greater than " + MIN_NUM_OF_USERS);
        }

        int locationLength = event.getLocation().length();
        if (locationLength < LOCATION_MIN_LENGTH || locationLength > LOCATION_MAX_LENGTH) {
            throw new EventValidationException("Event location should be between %d and %d characters"
                    .formatted(LOCATION_MIN_LENGTH, LOCATION_MAX_LENGTH));
        }

        if (event.getDateTime().isBefore(LocalDateTime.now())) {
            throw new EventValidationException("Event date should be after now");
        }
    }

    private static void checkNullParams(Event event) {
        if (event == null) {
            throw new EventValidationException("Event is null");
        }
        if (event.getName() == null || event.getName().isEmpty()) {
            throw new EventValidationException("Event name is empty");
        }
        if (event.getDateTime() == null) {
            throw new EventValidationException("Event datetime is null");
        }
        if (event.getLocation() == null || event.getLocation().isEmpty()) {
            throw new EventValidationException("Event location is empty");
        }
    }

}
