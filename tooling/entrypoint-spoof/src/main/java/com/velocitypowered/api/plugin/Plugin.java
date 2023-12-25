package com.velocitypowered.api.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Fake Velocity Plugin annotation class to simplify the creation of entrypoints. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {
    String id();

    String name() default "";

    String version() default "";

    String description() default "";

    String url() default "";

    String[] authors() default "";

    Dependency[] dependencies() default {};
}
