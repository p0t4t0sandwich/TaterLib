package dev.neuralnexus.taterlib.v1_7_10.fabric.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.server.FabricServer;
import dev.neuralnexus.taterlib.server.SimpleServer;

import net.minecraft.server.MinecraftServer;

public class FabricServerEvent implements ServerEvent {
    private final MinecraftServer server;

    public FabricServerEvent(MinecraftServer server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return new FabricServer(server);
    }
}
