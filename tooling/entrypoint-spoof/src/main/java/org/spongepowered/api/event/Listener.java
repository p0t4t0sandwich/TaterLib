package org.spongepowered.api.event;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** Fake annotation class to allow for Sponge start/stop events. */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Listener {}
