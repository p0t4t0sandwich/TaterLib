/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.muxins.annotations;

import dev.neuralnexus.modapi.metadata.enums.Platform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Requires the specified platforms to be present or not present for the mixin to be applied */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ReqPlatform {
    /**
     * The list of required platforms
     *
     * @return The list of required platforms
     */
    Platform[] value() default {};

    /**
     * The list of platforms that are not allowed
     *
     * @return The list of platforms that are not allowed
     */
    Platform[] not() default {};
}
