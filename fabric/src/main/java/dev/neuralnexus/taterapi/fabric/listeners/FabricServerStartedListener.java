package dev.neuralnexus.taterapi.fabric.listeners;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.fabric.FabricMain;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class FabricServerStartedListener implements ServerLifecycleEvents.ServerStarted {
    FabricMain plugin = FabricMain.getInstance();

    @Override
    public void onServerStarted(MinecraftServer server) {
        try {
            // Start LPPronouns
            plugin.taterApi = new TaterAPI("config", plugin.logger);
            plugin.taterApi.start();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
