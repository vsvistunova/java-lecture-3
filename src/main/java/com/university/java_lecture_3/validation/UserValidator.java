package com.university.java_lecture_3.validation;

import com.university.java_lecture_3.exception.UserValidationException;
import com.university.java_lecture_3.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator<User, UserValidationException> {

    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 100;
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 64;
    private static final String EMAIL_REGEX = "^([a-z0-9_.-]+)@([\\da-z.-]+)\\.([a-z.]{2,6})$";

    @Override
    public void validate(User user) throws UserValidationException {
        if (user == null) {
            throw new UserValidationException("User is null");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new UserValidationException("User's name is empty");
        }
        if (user.getAge() == null) {
            throw new UserValidationException("User's age is empty");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new UserValidationException("User's email is empty");
        }

        if (user.getAge() < MIN_AGE || user.getAge() > MAX_AGE) {
            throw new UserValidationException("User's age must be between 0 and 100");
        }
        if (user.getName().length() < NAME_MIN_LENGTH || user.getName().length() > NAME_MAX_LENGTH) {
            throw new UserValidationException("User's name must be between 3 and 64");
        }
        if (!user.getEmail().matches(EMAIL_REGEX)) {
            throw new UserValidationException("User's email address is invalid");
        }
    }

}
