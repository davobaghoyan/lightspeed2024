package org.lightspeed.utility;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class CopyUtils {

    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(T object) {
        if (object == null) {
            return null;
        }

        if (object instanceof String || object instanceof Character || object instanceof Number || object instanceof Boolean) {
            return (T) object;
        }

        Class<?> clazz = object.getClass();
        if (clazz.isArray()) {
            return (T) copyArray(object);
        } else if (object instanceof Collection) {
            return (T) copyCollection((Collection<?>) object);
        } else if (object instanceof Map) {
            return (T) copyMap((Map<?, ?>) object);
        }

        return (T) copyObject(object);
    }

    private static Object copyArray(Object array) {
        int length = Array.getLength(array);
        Object newArray = Array.newInstance(array.getClass().getComponentType(), length);

        for (int i = 0; i < length; i++) {
            Array.set(newArray, i, deepCopy(Array.get(array, i)));
        }

        return newArray;
    }

    @SuppressWarnings("unchecked")
    private static Collection<Object> copyCollection(Collection<?> collection) {
        Collection<Object> newCollection;

        try {
            newCollection = (Collection<Object>) collection.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            newCollection = new ArrayList<>();
        }

        for (Object item : collection) {
            newCollection.add(deepCopy(item));
        }

        return newCollection;
    }

    @SuppressWarnings("unchecked")
    private static Map<Object, Object> copyMap(Map<?, ?> map) {
        Map<Object, Object> newMap;

        try {
            newMap = (Map<Object, Object>) map.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            newMap = new HashMap<>();
        }

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = deepCopy(entry.getKey());
            Object value = deepCopy(entry.getValue());
            newMap.put(key, value);
        }

        return newMap;
    }

    private static Object copyObject(Object object) {
        Class<?> clazz = object.getClass();
        try {
            Object newObject = clazz.getConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                Object fieldValue = field.get(object);
                field.set(newObject, deepCopy(fieldValue));
            }

            return newObject;
        } catch (Exception e) {
            throw new RuntimeException("Failed to copy object", e);
        }
    }
}