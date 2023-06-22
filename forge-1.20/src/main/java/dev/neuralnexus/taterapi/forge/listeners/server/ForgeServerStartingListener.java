package dev.neuralnexus.taterapi.forge.listeners.server;

import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeServerStartingListener {
    @SubscribeEvent
    public void onServerStart(ServerStartingEvent event) {
        try {

        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
