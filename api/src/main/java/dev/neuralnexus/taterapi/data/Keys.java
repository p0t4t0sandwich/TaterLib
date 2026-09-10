/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.data;

import dev.neuralnexus.taterapi.data.action.Action;
import dev.neuralnexus.taterapi.data.action.Action1;
import dev.neuralnexus.taterapi.data.action.Action2;
import dev.neuralnexus.taterapi.data.action.Action3;
import dev.neuralnexus.taterapi.data.action.Action4;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.meta.anno.Range;
import dev.neuralnexus.taterapi.meta.anno.VersionFeature;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.resources.Identifier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public final class Keys {
    /** A living entity's heath values. See {@link dev.neuralnexus.taterapi.entity.Damageable} */
    public static final Key<Value<Double>> ABSORPTION = value("absorption", Double.class);

    public static final Key<Value<Double>> HEALTH = value("health", Double.class);
    public static final Key<Value<Double>> MAX_HEALTH = value("max_health", Double.class);

    public static final Key<Action1<Void, Double>> DAMAGE_GENERIC = action1("damage", void.class);
    public static final Key<Action2<Void, Double, Entity>> DAMAGE_ENTITY =
            action2("damage", void.class);

    /**
     * Used for objects that can be represented by a UUID. See {@link
     * dev.neuralnexus.taterapi.entity.Identifiable}
     */
    public static final Key<Value<UUID>> UUID = value("uuid", UUID.class);

    /**
     * An entity's custom name, often set via a name tag item. See {@link
     * dev.neuralnexus.taterapi.entity.Nameable}
     */
    // TODO: Use components
    @VersionFeature(name = "Keys.CUSTOM_NAME", compatible = @Range(min = MinecraftVersion.V6_1))
    public static final Key<Value<@Nullable String>> CUSTOM_NAME =
            value("custom_name", String.class);

    /**
     * An entity's display name. Attempts to resolve to their CUSTOM_NAME, their scoreboard name,
     * and finally defers to their NAME. See {@link dev.neuralnexus.taterapi.entity.Nameable}
     */
    // TODO: Use components
    public static final Key<Value<String>> DISPLAY_NAME = value("display_name", String.class);

    /**
     * An entity's name, if you want a static name value this is usually the property to go for. See
     * {@link dev.neuralnexus.taterapi.entity.Nameable}
     */
    public static final Key<Value<String>> NAME = value("name", String.class);

    /** A player's game mode. See {@link dev.neuralnexus.taterapi.entity.HumanEntity} */
    public static final Key<Value<GameMode>> GAME_MODE = value("game_mode", GameMode.class);

    @SuppressWarnings("unchecked")
    private static <T, E extends Value<?>> Key<E> value(
            final @NonNull String path, final @NonNull Class<T> type) {
        return (Key<E>) Key.create(Identifier.of("taterlib", path), Value.class, type);
    }

    @SuppressWarnings("unchecked")
    private static <R, E extends Action<?>> Key<E> action(
            final @NonNull String path, final @NonNull Class<R> type) {
        return (Key<E>) Key.create(Identifier.of("taterlib", path), Action.class, type);
    }

    @SuppressWarnings("unchecked")
    private static <R, E extends Action1<?, ?>> Key<E> action1(
            final @NonNull String path, final @NonNull Class<R> type) {
        return (Key<E>) Key.create(Identifier.of("taterlib", path), Action1.class, type);
    }

    @SuppressWarnings("unchecked")
    private static <R, E extends Action2<?, ?, ?>> Key<E> action2(
            final @NonNull String path, final @NonNull Class<R> type) {
        return (Key<E>) Key.create(Identifier.of("taterlib", path), Action2.class, type);
    }

    @SuppressWarnings("unchecked")
    private static <R, E extends Action3<?, ?, ?, ?>> Key<E> action3(
            final @NonNull String path, final @NonNull Class<R> type) {
        return (Key<E>) Key.create(Identifier.of("taterlib", path), Action3.class, type);
    }

    @SuppressWarnings("unchecked")
    private static <R, E extends Action4<?, ?, ?, ?, ?>> Key<E> action4(
            final @NonNull String path, final @NonNull Class<R> type) {
        return (Key<E>) Key.create(Identifier.of("taterlib", path), Action4.class, type);
    }
}
