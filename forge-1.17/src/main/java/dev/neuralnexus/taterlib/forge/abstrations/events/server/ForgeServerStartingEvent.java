package dev.neuralnexus.taterlib.forge.abstrations.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartingEvent;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;

/**
 * Forge implementation of {@link AbstractServerStartingEvent}.
 */
public class ForgeServerStartingEvent extends ForgeServerEvent implements AbstractServerStartingEvent {
    public ForgeServerStartingEvent(FMLServerStartingEvent event) {
        super(event);
    }
}
