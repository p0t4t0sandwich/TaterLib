package dev.neuralnexus.taterlib.vanilla.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.vanilla.server.VanillaServer;

import net.minecraft.server.MinecraftServer;

public class VanillaServerEvent implements ServerEvent {
    private final MinecraftServer server;

    public VanillaServerEvent(MinecraftServer server) {
        this.server = server;
        VanillaServer.setServer(server);
    }

    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return VanillaServer.getInstance();
    }
}
