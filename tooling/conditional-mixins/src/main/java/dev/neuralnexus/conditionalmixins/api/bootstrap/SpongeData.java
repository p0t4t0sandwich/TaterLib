package dev.neuralnexus.conditionalmixins.api.bootstrap;

import dev.neuralnexus.conditionalmixins.mixin.utils.MixinServiceUtils;
import org.spongepowered.api.Sponge;

import java.io.IOException;
import java.util.NoSuchElementException;

public class SpongeData implements BootstrapData {
    @Override
    public String MinecraftVersion() {
        try {
            // Reflect to get Sponge.platform().minecraftVersion().name()
            Object spongeInstance = Sponge.class.getMethod("platform").invoke(null);
            Object minecraftVersion =
                    spongeInstance.getClass().getMethod("minecraftVersion").invoke(spongeInstance);
            Object minecraftVersionName =
                    minecraftVersion.getClass().getMethod("name").invoke(minecraftVersion);
            return (String) minecraftVersionName;
        } catch (ReflectiveOperationException ignored) {
        }
        try {
            // Fall back to Sponge.getPlatform().getMinecraftVersion().getName()
            Object spongeInstance = Sponge.class.getMethod("getPlatform").invoke(null);
            Object minecraftVersion =
                    spongeInstance.getClass().getMethod("getMinecraftVersion").invoke(null);
            Object minecraftVersionName =
                    minecraftVersion.getClass().getMethod("getName").invoke(minecraftVersion);
            return (String) minecraftVersionName;
        } catch (ReflectiveOperationException ignored) {
        }
        try {
            return MixinServiceUtils.mcVersion();
        } catch (ClassNotFoundException | IOException | NoSuchElementException ignored) {
        }
        return "Unknown";
    }
}
