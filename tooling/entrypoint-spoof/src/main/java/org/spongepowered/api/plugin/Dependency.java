/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package org.spongepowered.api.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Fake annotation class to keep dependency information. <br>
 * Still need to use the SpongeGradle tool/annotationProcessor to generate the
 * META-INF/sponge_plugins.json file. The rest of the information can just be generated with
 * gradle.properties.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Dependency {
    String id();

    String version() default "";

    boolean optional() default false;
}
