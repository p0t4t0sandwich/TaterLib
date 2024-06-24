package dev.neuralnexus.taterlib.v1_7_10.forge.event.server;

import cpw.mods.fml.common.event.FMLStateEvent;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_7_10.forge.ForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_7_10.forge.server.ForgeServer;

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
