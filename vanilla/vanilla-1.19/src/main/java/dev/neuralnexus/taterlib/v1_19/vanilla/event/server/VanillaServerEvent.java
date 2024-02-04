package dev.neuralnexus.taterlib.v1_19.vanilla.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_19.vanilla.server.VanillaServer;

import net.minecraft.server.MinecraftServer;

public class VanillaServerEvent implements ServerEvent {
    public VanillaServerEvent(MinecraftServer server) {
        new VanillaServer(server);
    }

    /** {@inheritDoc} */
    @Override
    public SimpleServer getServer() {
        return VanillaServer.getInstance();
    }
}
