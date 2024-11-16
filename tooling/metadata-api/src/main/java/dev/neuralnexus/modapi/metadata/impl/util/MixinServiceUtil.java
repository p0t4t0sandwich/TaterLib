/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.util;

import org.spongepowered.asm.service.MixinService;

import java.io.IOException;

/** Helper/wrapper class to prevent ClassNotFound errors when Mixin is not present. */
public class MixinServiceUtil {
    /**
     * Returns the Minecraft version.
     *
     * @return The Minecraft version.
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static String mcVersion() throws ClassNotFoundException, IOException {
        // Fine to do since obfuscated situations are covered by Forge
        // Reflect to get SharedConstants.VERSION_STRING
        return (String)
                MixinService.getService()
                        .getBytecodeProvider()
                        .getClassNode("net.minecraft.SharedConstants")
                        .fields
                        .stream()
                        .filter(field -> field.name.equals("VERSION_STRING"))
                        .findFirst()
                        .get()
                        .value;
    }
}
