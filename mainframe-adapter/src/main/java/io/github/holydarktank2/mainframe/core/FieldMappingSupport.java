package io.github.holydarktank2.mainframe.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class FieldMappingSupport {
    private FieldMappingSupport() {
    }

    public static Map<String, Object> extractMappedFields(Object source) {
        Map<String, Object> values = new HashMap<>();
        for (Field field : source.getClass().getDeclaredFields()) {
            FieldMapping mapping = field.getAnnotation(FieldMapping.class);
            if (mapping == null) {
                continue;
            }
            field.setAccessible(true);
            Object fieldValue = readField(field, source);
            if (mapping.required() && isBlank(fieldValue)) {
                throw new ConversionException("Missing required field: " + field.getName());
            }
            if (fieldValue != null) {
                values.put(mapping.section() + "." + mapping.field(), fieldValue);
            }
        }
        return values;
    }

    private static Object readField(Field field, Object source) {
        try {
            return field.get(source);
        } catch (IllegalAccessException exception) {
            throw new ConversionException("Cannot read field: " + field.getName());
        }
    }

    private static boolean isBlank(Object value) {
        return value == null || (value instanceof String text && text.isBlank());
    }
}
