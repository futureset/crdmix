package com.crdmix.unit.config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class UnitUtil {

    private UnitUtil() {
        super();
    }

    public static List<Field> mutableFields(Class<?> clazz) {
        Class<?> currentClass = clazz;
        List<Field> fields = new ArrayList<>();
        do {
            fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();

        } while (currentClass != Object.class);
        fields.removeIf(f -> Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers()));
        return fields;
    }
}
