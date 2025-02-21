/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.muxins.annotations;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.Mappings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Requires the chosen runtime mappings to be present for the mixin to be applied */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ReqMappings {
    /**
     * The required mappings
     *
     * @return The required mappings
     */
    Mappings value();
}
