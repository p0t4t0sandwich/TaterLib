/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.meta.Constraint;
import dev.neuralnexus.taterapi.meta.Constraints;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.anno.Range;
import dev.neuralnexus.taterapi.meta.anno.VersionFeature;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

// TODO: Create new interface for names, and have this for name tags
/** Represents a nameable entity */
public interface Nameable extends DataHolder {
    /**
     * Get the name of the entity
     *
     * @return The name of the entity
     */
    default @NonNull String name() {
        return this.getOrThrow(Keys.NAME).get();
    }

    /**
     * Get the display name of the entity
     *
     * @return The display name of the entity
     */
    default @NonNull String displayName() {
        return this.getOrThrow(Keys.DISPLAY_NAME).get();
    }

    /**
     * Check if the entity has a custom name
     *
     * @return True if the entity has a custom name, false otherwise
     */
    @VersionFeature(
            name = "Nameable#hasCustomName",
            compatible = @Range(min = MinecraftVersion.V6_1))
    default boolean hasCustomName() {
        return this.customName().get() != null;
    }

    /**
     * Get the custom name of the entity
     *
     * @return The custom name of the entity
     */
    @VersionFeature(name = "Nameable#customName", compatible = @Range(min = MinecraftVersion.V6_1))
    default Value<@Nullable String> customName() {
        final Optional<Value<@Nullable String>> v = this.get(Keys.CUSTOM_NAME);
        if (v.isEmpty()
                && Constraints.builder()
                        .or(
                                Constraint.lessThan(MinecraftVersions.V6_1),
                                Constraint.builder()
                                        .platform(Platform.BUNGEECORD, Platform.VELOCITY))
                        .result()) {
            throw new VersionFeatureNotSupportedException();
        }
        return v.orElseThrow();
    }
}
