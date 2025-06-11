/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta.forge;

import net.minecraftforge.fml.loading.FMLLoader;

import java.lang.reflect.Field;

final class ForgeVersion_13_16 {
    public static String forgeVersion() {
        try {
            Field field = FMLLoader.class.getDeclaredField("forgeVersion");
            field.setAccessible(true);
            return (String) field.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return "Unknown";
        }
    }
}
