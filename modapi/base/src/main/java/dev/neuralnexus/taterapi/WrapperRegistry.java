/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class WrapperRegistry {
    private static final Map<Class<?>, Function<Object, ?>> WRAPPERS = new HashMap<>();

    public static <T, W> void register(Class<T> clazz, Function<T, W> wrapper) {
        WRAPPERS.put(clazz, (Function<Object, Wrapped<?>>) wrapper);
    }

    public static <T, R> R wrap(T object) {
        return (R) WRAPPERS.get(object.getClass()).apply(object);
    }
}
