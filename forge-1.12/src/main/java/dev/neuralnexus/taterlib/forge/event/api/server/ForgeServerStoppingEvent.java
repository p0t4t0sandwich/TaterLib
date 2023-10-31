package dev.neuralnexus.taterlib.forge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStoppingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

/**
 * Forge implementation of {@link AbstractServerStoppingEvent}.
 */
public class ForgeServerStoppingEvent extends ForgeServerEvent implements AbstractServerStoppingEvent {
    public ForgeServerStoppingEvent(FMLServerStoppingEvent event) {
        super(event);
    }
}
