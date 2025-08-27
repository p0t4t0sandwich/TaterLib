/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.minecraftforge.fml.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * BORROWED FROM: <a
 * href="https://github.com/kosmolot-mods/minecraft-mysql-jdbc/blob/main/forge-1.12/src/main/java/net/minecraftforge/fml/common/Mod.java">kosma</a>
 * <br>
 *
 * <p>Fake Forge Mod annotation class capable of targeting both old-style 1.12 loader and new-style
 * 1.13 loader. This puppy both saves us the PAIN of loading the entire Forge toolchain (and hours
 * of waiting) and the PAIN of Forge doing its bad heuristics to block the mod from being universal.
 *
 * <p>Of course, this package is later excluded from the build on the jar stage.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mod {
    String modid() default "";

    String value();

    String acceptableRemoteVersions() default "";

    // My additions
    boolean useMetadata() default false;

    boolean serverSideOnly() default false;

    // Tater additions
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @interface EventHandler {}
}
