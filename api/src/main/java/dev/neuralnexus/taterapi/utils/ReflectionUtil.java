/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.utils;

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
}
