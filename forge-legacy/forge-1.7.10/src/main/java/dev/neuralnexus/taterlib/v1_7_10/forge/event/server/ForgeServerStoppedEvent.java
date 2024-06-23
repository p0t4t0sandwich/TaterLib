package dev.neuralnexus.taterlib.v1_7_10.forge.event.server;

import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;

/** Forge implementation of {@link ServerStoppedEvent}. */
public class ForgeServerStoppedEvent extends ForgeServerEvent implements ServerStoppedEvent {
  public ForgeServerStoppedEvent(FMLServerStoppedEvent event) {
    super(event);
  }
}
