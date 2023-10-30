package dev.neuralnexus.taterlib.forge.abstrations.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppedEvent;
import net.minecraftforge.fmlserverevents.FMLServerStoppedEvent;

/**
 * Forge implementation of {@link AbstractServerStoppedEvent}.
 */
public class ForgeServerStoppedEvent extends ForgeServerEvent implements AbstractServerStoppedEvent {
    public ForgeServerStoppedEvent(FMLServerStoppedEvent event) {
        super(event);
    }
}
