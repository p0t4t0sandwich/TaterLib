package dev.neuralnexus.taterapi.fabric;

import dev.neuralnexus.taterapi.common.TaterAPI;
import net.fabricmc.api.DedicatedServerModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricMain implements DedicatedServerModInitializer {
    public static TaterAPI taterApi;

    // Logger
    public static final Logger logger = LoggerFactory.getLogger("template");

    // Get server type
    public String getServerType() {
        return "Fabric";
    }

    @Override
    public void onInitializeServer() {
        logger.info("[TaterAPI]: TaterAPI is running on " + getServerType() + ".");

        // Start TaterAPI
        taterApi = new TaterAPI("config", FabricMain.logger);
        TaterAPI.start();

        // Mod enable message
        logger.info("[TaterAPI]: TaterAPI has been enabled!");
    }
}
