package com.university.java_lecture_3.validation;

public interface Validator<T, E extends RuntimeException> {

    void validate(T t) throws E;

}
