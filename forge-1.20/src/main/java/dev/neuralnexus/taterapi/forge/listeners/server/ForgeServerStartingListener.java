package dev.neuralnexus.taterapi.forge.listeners.server;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.forge.ForgeMain;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeServerStartingListener {
    @SubscribeEvent
    public void onServerStart(ServerStartingEvent event) {
        try {
            // Start TaterAPI
            ForgeMain.taterApi = new TaterAPI("config", ForgeMain.logger);
            TaterAPI.start();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
