package dev.neuralnexus.taterlib.fabric.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartedEvent;
import net.minecraft.server.MinecraftServer;

/**
 * Fabric implementation of {@link ServerStartedEvent}.
 */
public class FabricServerStartedEvent extends FabricServerEvent implements ServerStartedEvent {
    public FabricServerStartedEvent(MinecraftServer server) {
        super(server);
    }
}
