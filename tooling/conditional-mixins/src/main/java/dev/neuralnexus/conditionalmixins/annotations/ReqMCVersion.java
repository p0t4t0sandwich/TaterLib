package dev.neuralnexus.conditionalmixins.annotations;

import dev.neuralnexus.conditionalmixins.api.MinecraftVersion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ReqMCVersion {
    MinecraftVersion[] value();
    MinecraftVersion min() default MinecraftVersion.UNKNOWN;
    MinecraftVersion max() default MinecraftVersion.UNKNOWN;
}
