/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.data;

import dev.neuralnexus.taterapi.data.value.TaterValue;
import dev.neuralnexus.taterapi.data.value.TaterValueFactory;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.registries.BuilderRegistry;
import dev.neuralnexus.taterapi.registries.FactoryRegistry;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class TaterDataHolder implements DataHolder {
    static {
        BuilderRegistry.register(Key.Builder.class, TaterBuilder::new);
        FactoryRegistry.register(Value.Factory.class, TaterValueFactory::new);
    }

    private final HashMap<Key<?>, TaterValue<?>> STORE = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <E> Optional<E> offer(final @NonNull Key<? extends Value<E>> key, final E value) {
        final TaterValue<E> v = (TaterValue<E>) this.STORE.get(key);
        if (v == null) return Optional.empty();
        return Optional.of(v.set(value).get());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> Optional<E> get(final @NonNull Key<? extends Value<E>> key) {
        final TaterValue<E> v = (TaterValue<E>) this.STORE.get(key);
        if (v == null) return Optional.empty();
        return Optional.of(v.get());
    }

    @Override
    public Set<Key<?>> getKeys() {
        return this.STORE.keySet();
    }

    @ApiStatus.Internal
    public <E> void register(final @NonNull Value<E> value) {
        this.STORE.put(value.key(), (TaterValue<?>) value);
    }

    @ApiStatus.Internal
    @SafeVarargs
    public final <E> void register(final @NonNull Value<E>... values) {
        for (Value<E> value : values) {
            this.STORE.put(value.key(), (TaterValue<?>) value);
        }
    }
}
