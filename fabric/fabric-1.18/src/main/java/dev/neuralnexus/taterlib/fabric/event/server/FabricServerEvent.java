package dev.neuralnexus.taterlib.fabric.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerEvent;
import dev.neuralnexus.taterlib.common.server.Server;
import dev.neuralnexus.taterlib.fabric.server.FabricServer;

import net.minecraft.server.MinecraftServer;

public class FabricServerEvent implements ServerEvent {
    private final MinecraftServer server;

    public FabricServerEvent(MinecraftServer server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new FabricServer(server);
    }
}
