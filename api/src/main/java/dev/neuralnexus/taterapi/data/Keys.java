/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.data;

import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.resources.Identifier;

import org.jspecify.annotations.NonNull;

public final class Keys {
    public static final Key<Value<Double>> ABSORPTION = key("absorption", Double.class);
    public static final Key<Value<Double>> HEALTH = key("health", Double.class);
    public static final Key<Value<Double>> MAX_HEALTH = key("max_health", Double.class);

    private static <E> Key<Value<E>> key(final @NonNull String path, final @NonNull Class<E> type) {
        return Key.from(Identifier.of("taterlib", path), type);
    }
}
