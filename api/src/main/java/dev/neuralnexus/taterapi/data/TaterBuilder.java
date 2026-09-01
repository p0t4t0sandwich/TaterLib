/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.data;

import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.resources.Identifier;

import org.jspecify.annotations.NonNull;

public class TaterBuilder<E, V extends Value<E>> implements Key.Builder<E, V> {
    private Identifier identifier;
    private Class<V> type;

    @SuppressWarnings("unchecked")
    @Override
    public <T, B extends Value<T>> Key.Builder<T, B> type(@NonNull Class<T> type) {
        this.type = (Class<V>) type;
        return (Key.Builder<T, B>) this;
    }

    @Override
    public Key.Builder<E, V> key(@NonNull Identifier identifier) {
        this.identifier = identifier;
        return this;
    }

    @Override
    public Key<V> build() {
        return new TaterKey<>(identifier, type);
    }
}
