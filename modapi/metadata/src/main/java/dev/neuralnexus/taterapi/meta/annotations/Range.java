/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.annotations;

import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** An annotation to specify a range of Minecraft versions for an annotation */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Range {
    /**
     * The required Minecraft version
     *
     * @return The required Minecraft version
     */
    MinecraftVersion value() default MinecraftVersion.UNKNOWN;

    /**
     * The required Minecraft versions
     *
     * @return The required Minecraft versions
     */
    MinecraftVersion[] values() default {};

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
