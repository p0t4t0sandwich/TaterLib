package dev.neuralnexus.taterlib.v1_12_2.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.v1_12_2.forge.ForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_12_2.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.server.SimpleServer;

import net.minecraftforge.fml.common.event.FMLStateEvent;

/** Forge implementation of {@link ServerEvent}. */
public class ForgeServerEvent implements ServerEvent {
    private final FMLStateEvent event;

    public ForgeServerEvent(FMLStateEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return new ForgeServer(ForgeTaterLibPlugin.minecraftServer);
    }
}
