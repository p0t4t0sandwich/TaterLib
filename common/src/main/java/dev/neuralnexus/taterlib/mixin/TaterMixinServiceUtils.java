package dev.neuralnexus.taterlib.mixin;

import org.spongepowered.asm.service.MixinService;

import java.io.IOException;

/** Helper/wrapper class to prevent ClassNotFound errors when Mixin is not present. */
public class TaterMixinServiceUtils {
    /**
     * Returns the Minecraft version.
     *
     * @return The Minecraft version.
     */
    public static String getMCVersion() throws ClassNotFoundException, IOException {
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
