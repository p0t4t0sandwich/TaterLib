package org.spongepowered.api.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Fake Sponge 7- Plugin annotation class to simplify the creation of entrypoints. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {
    String id();

    String name() default "";

    String version() default "";

    Dependency[] dependencies() default {};

    String description() default "";

    String url() default "";

    String[] authors() default {};
}
