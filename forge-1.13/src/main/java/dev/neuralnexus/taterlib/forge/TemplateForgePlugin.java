package dev.neuralnexus.taterlib.forge;

import dev.neuralnexus.taterlib.common.TemplatePlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.loading.FMLLoader;

import java.lang.reflect.Field;

public class TemplateForgePlugin implements TemplatePlugin {
    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String pluginConfigPath() {
        return "config";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerType() {
        return "Forge";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerVersion() {
        // Reflect to get the Minecraft and Forge versions from FMLLoader
        String mcVersion = null;
        String forgeVersion = null;

        try {
            Field mcVersionField = FMLLoader.class.getDeclaredField("mcVersion");
            mcVersionField.setAccessible(true);
            mcVersion = (String) mcVersionField.get(null);
            Field forgeVersionField = FMLLoader.class.getDeclaredField("forgeVersion");
            forgeVersionField.setAccessible(true);
            forgeVersion = (String) forgeVersionField.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return mcVersion + "-" + forgeVersion;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerHooks() {}

    /**
     * Called when the server is starting.
     * @param event The event.
     */
    @SubscribeEvent
    public void onServerStarted(FMLServerStartedEvent event) {
        registerHooks();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {}

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {}
}
