package dev.neuralnexus.conditionalmixins.api.bootstrap.forge;

import dev.neuralnexus.conditionalmixins.api.bootstrap.BootstrapData;
import net.minecraftforge.fml.loading.FMLLoader;

import java.lang.reflect.Field;

public class FMLLoaderData implements BootstrapData {
    @Override
    public String MinecraftVersion() {
        try {
            try {
                // Reflect to get FMLLoader.versionInfo().mcVersion()
                Object versionInfoObject = FMLLoader.class.getMethod("versionInfo").invoke(null);
                return (String) versionInfoObject
                        .getClass()
                        .getMethod("mcVersion")
                        .invoke(versionInfoObject);
            } catch (ReflectiveOperationException e) {
                // Reflect to get private FMLLoader.mcVersion
                Field mcVersionField = FMLLoader.class.getDeclaredField("mcVersion");
                mcVersionField.setAccessible(true);
                return (String) mcVersionField.get(null);
            }
        } catch (ReflectiveOperationException e) {
            return "Unknown";
        }
    }
}
