/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package com.velocitypowered.api.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Fake annotation class to keep dependency information. <br>
 * Still need to use the velocity-api annotationProcessor to generate the velocity-plugin.json file.
 * The rest of the information can just be generated with gradle.properties.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Dependency {
    String id();

    boolean optional() default false;
}
