/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.data.value;

import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.TaterKey;

import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TaterValue<E> implements Value<E> {
    private final TaterKey<? extends Value<E>, E> key;
    private final Supplier<E> GET;
    private final Consumer<E> SET;
    private final boolean mutable;

    public TaterValue(
            final @NonNull Key<? extends Value<E>> key,
            final @NonNull Supplier<E> GET,
            final @NonNull Consumer<E> SET,
            final boolean mutable) {
        this.key = (TaterKey<? extends Value<E>, E>) key;
        this.GET = GET;
        this.SET = SET;
        this.mutable = mutable;
    }

    @Override
    public E get() {
        return this.GET.get();
    }

    @Override
    public Value<E> set(final E value) {
        if (this.isMutable()) {
            this.SET.accept(value);
        }
        return this;
    }

    @Override
    public boolean isMutable() {
        return this.mutable;
    }

    @Override
    public Key<? extends Value<E>> key() {
        return this.key;
    }
}
