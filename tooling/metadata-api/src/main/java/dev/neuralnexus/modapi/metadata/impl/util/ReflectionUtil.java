/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.util;

import dev.neuralnexus.modapi.metadata.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/** Utility class for reflection operations */
public class ReflectionUtil {
    /**
     * Check if a class exists.
     *
     * @param className The class(es) to check.
     * @return Whether the class exists.
     */
    public static boolean checkForClass(String... className) {
        return Arrays.stream(className)
                .anyMatch(
                        s -> {
                            try {
                                Class.forName(s);
                                return true;
                            } catch (ClassNotFoundException e) {
                                return false;
                            }
                        });
    }

    /**
     * Check if a method exists.
     *
     * @param className The class to check.
     * @param methodName The method to check.
     * @return Whether the method exists.
     */
    public static boolean checkForMethod(String className, String methodName) {
        try {
            Class.forName(className).getMethod(methodName);
            return true;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * Get an instance of a class
     *
     * @param clazz The full class name and path
     * @param args The constructor's parameters
     * @return A new instance of the object
     * @param <T> The object's type
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String clazz, Object... args) {
        try {
            Class<?> objectClass = Class.forName(clazz);
            return (T) objectClass.getConstructor().newInstance(args);
        } catch (ClassNotFoundException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException
                | NoSuchMethodException e) {
            Logger.create("ReflectionUtil")
                    .warn("Could not instantiate new instance of " + clazz, e);
        }
        return null;
    }
}
