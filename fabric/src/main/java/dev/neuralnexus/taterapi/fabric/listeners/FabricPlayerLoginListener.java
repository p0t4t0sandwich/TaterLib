package dev.neuralnexus.taterapi.fabric.listeners;

import dev.neuralnexus.taterapi.fabric.FabricMain;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

public class FabricPlayerLoginListener implements ServerPlayConnectionEvents.Join {
    FabricMain mod = FabricMain.getInstance();

    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        runTaskAsync(() -> {
            try {
                // Do stuff
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
