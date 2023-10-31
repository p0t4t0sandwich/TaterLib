package dev.neuralnexus.taterlib.forge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

/**
 * Forge implementation of {@link AbstractServerStoppedEvent}.
 */
public class ForgeServerStoppedEvent extends ForgeServerEvent implements AbstractServerStoppedEvent {
    public ForgeServerStoppedEvent(FMLServerStoppedEvent event) {
        super(event);
    }
}
