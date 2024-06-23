package dev.neuralnexus.taterlib.v1_7_10.forge.event.server;

import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

/** Forge implementation of {@link ServerStoppingEvent}. */
public class ForgeServerStoppingEvent extends ForgeServerEvent implements ServerStoppingEvent {
  public ForgeServerStoppingEvent(FMLServerStoppingEvent event) {
    super(event);
  }
}
