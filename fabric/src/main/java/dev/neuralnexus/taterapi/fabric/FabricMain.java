package dev.neuralnexus.taterapi.fabric;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.fabric.commands.FabricTemplateCommand;
import dev.neuralnexus.taterapi.fabric.listeners.FabricPlayerLoginListener;
import dev.neuralnexus.taterapi.fabric.listeners.FabricServerStartedListener;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricMain implements DedicatedServerModInitializer {
    public TaterAPI taterApi;

    // Logger
    public final Logger logger = LoggerFactory.getLogger("template");

    // Get server type
    public String getServerType() {
        return "Fabric";
    }

    // Singleton instance
    private static FabricMain instance;
    public static FabricMain getInstance() {
        return instance;
    }

    @Override
    public void onInitializeServer() {
        // Singleton instance
        instance = this;

        logger.info("[Template]: Template is running on " + getServerType() + ".");

        // Start Template
        taterApi = new TaterAPI("config", logger);
        taterApi.start();

        // Register event listeners
        ServerLifecycleEvents.SERVER_STARTED.register(new FabricServerStartedListener());
        ServerPlayConnectionEvents.JOIN.register(new FabricPlayerLoginListener());

        // Register commands
        CommandRegistrationCallback.EVENT.register(FabricTemplateCommand::register);

        // Mod enable message
        logger.info("[Template]: Template has been enabled!");
    }
}
