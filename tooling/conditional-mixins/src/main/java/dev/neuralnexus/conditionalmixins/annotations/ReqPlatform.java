package dev.neuralnexus.conditionalmixins.annotations;

import dev.neuralnexus.taterlib.loader.api.Platform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ReqPlatform {
    Platform[] value();
}
