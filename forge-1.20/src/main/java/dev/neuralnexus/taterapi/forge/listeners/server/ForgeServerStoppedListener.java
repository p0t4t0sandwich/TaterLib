package dev.neuralnexus.taterapi.forge.listeners.server;

import dev.neuralnexus.taterapi.common.TaterAPI;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeServerStoppedListener {
    @SubscribeEvent
    public void onServerStart(ServerStoppingEvent event) {
        try {
            // Stop TaterAPI
            TaterAPI.stop();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
