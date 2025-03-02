/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.util;

import dev.neuralnexus.taterapi.logger.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/** Utility class for reflection operations */
public final class ReflectionUtil {
    private static boolean isMixinPresent;

    static {
        try {
            Class.forName("org.spongepowered.asm.service.MixinService");
            isMixinPresent = true;
        } catch (ClassNotFoundException e) {
            isMixinPresent = false;
        }
    }

    /**
     * Check if a class exists
     *
     * @param className The class(es) to check
     * @return True if one of the classes exists
     */
    public static boolean checkForClass(String... className) {
        for (String s : className) {
            try {
                if (isMixinPresent) {
                    MixinServiceUtil.checkForClass(s);
                } else {
                    Class.forName(s);
                }
                return true;
            } catch (ClassNotFoundException | IOException ignored) {
            }
        }
        return false;
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
            if (isMixinPresent) {
                MixinServiceUtil.checkForMethod(className, methodName);
            } else {
                Class.forName(className).getDeclaredMethod(methodName);
            }
            return true;
        } catch (ClassNotFoundException | NoSuchMethodException | IOException e) {
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
