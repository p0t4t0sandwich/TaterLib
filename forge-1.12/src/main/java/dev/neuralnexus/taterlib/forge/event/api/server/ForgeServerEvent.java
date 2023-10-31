package dev.neuralnexus.taterlib.forge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerEvent;
import net.minecraftforge.fml.common.event.FMLStateEvent;

/**
 * Forge implementation of {@link AbstractServerEvent}.
 */
public class ForgeServerEvent implements AbstractServerEvent {
    private final FMLStateEvent event;

    public ForgeServerEvent(FMLStateEvent event) {
        this.event = event;
    }
}
