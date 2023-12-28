package net.minecraftforge.eventbus.api;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** Fake Forge event bus annotation. */
@Retention(value = RUNTIME)
@Target(value = METHOD)
public @interface SubscribeEvent {}
