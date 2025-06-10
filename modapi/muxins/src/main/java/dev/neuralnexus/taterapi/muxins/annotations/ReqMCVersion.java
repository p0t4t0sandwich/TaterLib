/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.muxins.annotations;

import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Requires the specified Minecraft versions or a range of versions to be present for the mixin to
 * be applied
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ReqMCVersion {
    /**
     * The list of required Minecraft versions
     *
     * @return The list of required Minecraft versions
     */
    MinecraftVersion[] value() default {};

    /**
     * The minimum required Minecraft version (inclusive)
     *
     * @return The minimum required Minecraft version
     */
    MinecraftVersion min() default MinecraftVersion.UNKNOWN;

    /**
     * The maximum required Minecraft version (inclusive)
     *
     * @return The maximum required Minecraft version
     */
    MinecraftVersion max() default MinecraftVersion.UNKNOWN;
}
