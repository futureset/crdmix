package com.crdmix.unit.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractUnitBase<T> {

    protected Class<T> clazz;
    protected T underTest;

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DontInject {

    }


    @BeforeEach
    public void setup() throws Exception {
        clazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        underTest = createInstance();
        Assertions.assertThat(getClass().getName())
                .as("Test class should be same name has class under test with 'Test' appended ")
                .isEqualTo(clazz.getName() + "Test");
    }

    @SuppressWarnings("unchecked")
    protected T createInstance() throws Exception {
        Constructor<?> selectCtor = null;
        for (Constructor<?> c : clazz.getConstructors()) {
            if (selectCtor == null || c.getParameterCount() > selectCtor.getParameterCount()) {
                selectCtor = c;
            }
        }
        Object[] args = new Object[selectCtor.getParameterCount()];
        Set<Field> fields = new LinkedHashSet<>();
        Class<?> scanClassForFields = getClass();
        do {
            fields.addAll(Arrays.asList(scanClassForFields.getDeclaredFields()));
            scanClassForFields = scanClassForFields.getSuperclass();
        } while (scanClassForFields != AbstractUnitBase.class);
        fields.removeIf(field -> field.getDeclaringClass() == AbstractUnitBase.class
                || (Modifier.isPrivate(field.getModifiers()) && field.getDeclaringClass() != getClass())
                || field.isAnnotationPresent(DontInject.class));
        for (int i = 0; i < args.length; i++) {
            Class<?> parameterType = selectCtor.getParameterTypes()[i];
            Optional<Field> matchingField = fields.stream().filter(f -> parameterType.isAssignableFrom(f.getType()))
                    .findFirst();
            if (matchingField.isPresent()) {
                Field field = matchingField.get();
                field.setAccessible(true);
                args[i] = field.get(this);
                field.setAccessible(false);
                fields.remove(field);
            } else {
                if (!parameterType.isPrimitive() && !Modifier.isFinal(parameterType.getModifiers())) {
                    System.out.println("@Mock");
                }
                System.out.println(parameterType.getSimpleName() + " "
                        + StringUtils.uncapitalize(parameterType.getSimpleName()) + ";");
            }
        }
        return (T) selectCtor.newInstance(args);
    }

}
