package dev.neuralnexus.taterapi.forge.listeners;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.forge.ForgeMain;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeServerStartedListener {
    ForgeMain plugin = ForgeMain.getInstance();

    @SubscribeEvent
    public void onServerStart(ServerStartingEvent event) {
        try {
            // Start Template
            plugin.taterApi = new TaterAPI("config", ForgeMain.logger);
            plugin.taterApi.start();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
