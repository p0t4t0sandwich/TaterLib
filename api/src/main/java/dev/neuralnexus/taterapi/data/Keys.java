/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.data;

import dev.neuralnexus.taterapi.data.value.Value;
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
    public static final Key<Value<Double>> ABSORPTION = key("absorption", Double.class);

    public static final Key<Value<Double>> HEALTH = key("health", Double.class);
    public static final Key<Value<Double>> MAX_HEALTH = key("max_health", Double.class);

    /**
     * Used for objects that can be represented by a UUID. See {@link
     * dev.neuralnexus.taterapi.entity.Identifiable}
     */
    public static final Key<Value<UUID>> UUID = key("uuid", UUID.class);

    /**
     * An entity's custom name, often set via a name tag item. See {@link
     * dev.neuralnexus.taterapi.entity.Nameable}
     */
    @VersionFeature(name = "Keys.CUSTOM_NAME", compatible = @Range(min = MinecraftVersion.V6_1))
    public static final Key<Value<@Nullable String>> CUSTOM_NAME = key("custom_name", String.class);

    /**
     * An entity's display name. Attempts to resolve to their CUSTOM_NAME, their scoreboard name,
     * and finally defers to their NAME. See {@link dev.neuralnexus.taterapi.entity.Nameable}
     */
    public static final Key<Value<String>> DISPLAY_NAME = key("display_name", String.class);

    /**
     * An entity's name, if you want a static name value this is usually the property to go for. See
     * {@link dev.neuralnexus.taterapi.entity.Nameable}
     */
    public static final Key<Value<String>> NAME = key("name", String.class);

    /** A player's game mode. See {@link dev.neuralnexus.taterapi.entity.HumanEntity} */
    public static final Key<Value<GameMode>> GAME_MODE = key("game_mode", GameMode.class);

    private static <E> Key<Value<E>> key(final @NonNull String path, final @NonNull Class<E> type) {
        return Key.from(Identifier.of("taterlib", path), type);
    }
}
