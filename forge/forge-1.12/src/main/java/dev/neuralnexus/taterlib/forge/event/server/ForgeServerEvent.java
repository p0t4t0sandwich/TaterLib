package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.forge.ForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.server.Server;

import net.minecraftforge.fml.common.event.FMLStateEvent;

/** Forge implementation of {@link ServerEvent}. */
public class ForgeServerEvent implements ServerEvent {
    private final FMLStateEvent event;

    public ForgeServerEvent(FMLStateEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new ForgeServer(ForgeTaterLibPlugin.getServer());
    }
}
