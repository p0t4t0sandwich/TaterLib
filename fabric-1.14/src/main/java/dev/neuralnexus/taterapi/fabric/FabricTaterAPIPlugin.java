package dev.neuralnexus.taterapi.fabric;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.TaterAPIPlugin;
import dev.neuralnexus.taterapi.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterapi.fabric.events.server.FabricServerStartingEvent;
import dev.neuralnexus.taterapi.fabric.events.server.FabricServerStoppedEvent;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;

/**
 * The TaterAPI Fabric plugin.
 */
public class FabricTaterAPIPlugin implements DedicatedServerModInitializer, TaterAPIPlugin {
    /**
     * @inheritDoc
     */
    @Override
    public Object pluginLogger() {
        return LogManager.getLogger("taterapi");
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
    public void registerHooks() {
        // Register LuckPerms hook
        FabricServerStartingEvent.EVENT.register(server -> {
            if (FabricLoader.getInstance().isModLoaded("luckperms")) {
                useLogger("[TaterAPI] LuckPerms detected, enabling LuckPerms hook.");
                TaterAPI.addHook(new LuckPermsHook());
            }
        });
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

    /**
     * @inheritDoc
     */
    @Override
    public void onInitializeServer() {
        pluginStart();
        FabricServerStoppedEvent.EVENT.register(server -> pluginStop());
    }
}
