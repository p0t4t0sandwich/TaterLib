package dev.neuralnexus.taterlib.fabric;

import dev.neuralnexus.taterlib.common.TemplatePlugin;
import dev.neuralnexus.taterlib.common.logger.AbstractLogger;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.server.MinecraftServer;

import java.util.Optional;

public class TemplateFabricPlugin implements DedicatedServerModInitializer, TemplatePlugin {
    public static MinecraftServer server;

    /**
     * Getter for the Minecraft server.
     * @return The Minecraft server.
     */
    public static MinecraftServer getServer() {
        return server;
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
        return "Fabric";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerVersion() {
        String version = "Unknown";
        Optional<ModContainer> minecraftModContainer = FabricLoader.getInstance().getModContainer("minecraft");
        if (minecraftModContainer.isPresent()) {
            version = minecraftModContainer.get().getMetadata().getVersion().getFriendlyString();
        }
        return version;
    }

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
    public void registerHooks() {}

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

    /**
     * @inheritDoc
     */
    @Override
    public void onInitializeServer() {}
}
