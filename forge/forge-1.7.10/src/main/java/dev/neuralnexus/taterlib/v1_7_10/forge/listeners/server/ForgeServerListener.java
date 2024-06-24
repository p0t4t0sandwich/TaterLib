package dev.neuralnexus.taterlib.v1_7_10.forge.listeners.server;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.server.ForgeServerStartedEvent;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.server.ForgeServerStartingEvent;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.server.ForgeServerStoppedEvent;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.server.ForgeServerStoppingEvent;

/** Listens for server events. */
public class ForgeServerListener {
  /**
   * Called when the server starts.
   *
   * @param event The server starting event
   */
  @Mod.EventHandler
  public void onServerStarting(FMLServerStartingEvent event) {
    ServerEvents.STARTING.invoke(new ForgeServerStartingEvent(event));
  }

  /**
   * Called when the server starts.
   *
   * @param event The server started event
   */
  @Mod.EventHandler
  public void onServerStarted(FMLServerStartedEvent event) {
    ServerEvents.STARTED.invoke(new ForgeServerStartedEvent(event));
  }

  /**
   * Called when the server stops.
   *
   * @param event The server stopping event
   */
  @Mod.EventHandler
  public void onServerStopping(FMLServerStoppingEvent event) {
    ServerEvents.STOPPING.invoke(new ForgeServerStoppingEvent(event));
  }

  /**
   * Called when the server stops.
   *
   * @param event The server stopped event
   */
  @Mod.EventHandler
  public void onServerStopped(FMLServerStoppedEvent event) {
    ServerEvents.STOPPED.invoke(new ForgeServerStoppedEvent(event));
  }
}
