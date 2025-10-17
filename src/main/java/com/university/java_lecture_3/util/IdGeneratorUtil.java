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
        if (ENTITY_ID_COUNTER.containsKey(clazz)) {
            return ENTITY_ID_COUNTER.get(clazz).incrementAndGet();
        } else {
            ENTITY_ID_COUNTER.put(clazz, new AtomicLong(INITIAL_ID));
            return ENTITY_ID_COUNTER.get(clazz).get();
        }
    }

}
