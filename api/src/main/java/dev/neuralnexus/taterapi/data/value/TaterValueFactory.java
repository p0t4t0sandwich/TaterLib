/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.data.value;

import dev.neuralnexus.taterapi.data.Key;

import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class TaterValueFactory implements Value.Factory {
    @Override
    public <V extends Value<E>, E> V mutableOf(
            @NonNull Key<V> key, @NonNull Supplier<E> GET, @NonNull Consumer<E> SET) {
        return (V) new TaterValue<>(key, GET, SET, true);
    }

    @Override
    public <V extends Value<E>, E> V immutableOf(@NonNull Key<V> key, @NonNull Supplier<E> GET) {
        return (V) new TaterValue<>(key, GET, (_) -> {}, false);
    }
}
