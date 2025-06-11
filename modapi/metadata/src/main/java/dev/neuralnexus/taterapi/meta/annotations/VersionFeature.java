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

/** An annotation to specify the compatibility of a feature with Minecraft versions */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.TYPE})
public @interface VersionFeature {
    /** The name of the feature */
    String name();

    /**
     * The compatible Minecraft versions ranges
     *
     * @return The compatible Minecraft versions ranges
     */
    Range[] compatible() default {};

    /**
     * The incompatible Minecraft versions ranges
     *
     * @return The incompatible Minecraft versions ranges
     */
    Range[] incompatible() default {};

    /**
     * The compatible Minecraft versions
     *
     * @return The compatible Minecraft versions
     */
    MinecraftVersion[] compatibleVersions() default {};

    /**
     * The incompatible Minecraft versions
     *
     * @return The incompatible Minecraft versions
     */
    MinecraftVersion[] incompatibleVersions() default {};
}
