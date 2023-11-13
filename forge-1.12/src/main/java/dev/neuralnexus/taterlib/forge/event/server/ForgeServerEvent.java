package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerEvent;
import net.minecraftforge.fml.common.event.FMLStateEvent;

/**
 * Forge implementation of {@link ServerEvent}.
 */
public class ForgeServerEvent implements ServerEvent {
    private final FMLStateEvent event;

    public ForgeServerEvent(FMLStateEvent event) {
        this.event = event;
    }
}
