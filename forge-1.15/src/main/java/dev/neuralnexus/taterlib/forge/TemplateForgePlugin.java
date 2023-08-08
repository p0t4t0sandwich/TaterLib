package dev.neuralnexus.taterlib.forge;

import dev.neuralnexus.taterlib.common.TemplatePlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.VersionInfo;
import net.minecraftforge.fmlserverevents.FMLServerStartedEvent;

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
        VersionInfo versionInfo = FMLLoader.versionInfo();
        return versionInfo.mcAndForgeVersion();
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
