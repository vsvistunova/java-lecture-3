package com.university.java_lecture_3.util;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@UtilityClass
public class IdGeneratorUtil {

    private static final int INITIAL_ID = 1;
    private static final Map<Class<?>, AtomicLong> ENTITY_ID_COUNTER;

    static {
        ENTITY_ID_COUNTER = new ConcurrentHashMap<>();
    }

    public static long generate(Class<?> clazz) {
        AtomicLong counter = ENTITY_ID_COUNTER.computeIfAbsent(clazz,
                k -> new AtomicLong(INITIAL_ID));
        return counter.getAndIncrement();
    }

}
