package dev.neuralnexus.taterlib.v1_7_10.forge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.command.ForgeCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.server.ForgeServerStartedEvent;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.server.ForgeServerStartingEvent;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.server.ForgeServerStoppedEvent;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.server.ForgeServerStoppingEvent;
import dev.neuralnexus.taterlib.v1_7_10.forge.hooks.permissions.ForgePermissionsHook;
import dev.neuralnexus.taterlib.v1_7_10.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.v1_7_10.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.v1_7_10.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.v1_7_10.forge.server.ForgeServer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

/** Forge entry point. */
public class ForgeTaterLibPlugin implements TaterLibPlugin {
  public static MinecraftServer minecraftServer;

  /**
   * Registers the TaterLib command.
   *
   * @param event The register commands event.
   */
  @Mod.EventHandler
  public static void registerCommand(FMLServerStartingEvent event) {
    CommandEvents.REGISTER_COMMAND.invoke(new ForgeCommandRegisterEvent(event));
  }

  @Override
  public void onInit(Object plugin, Object server, Object logger) {
    TaterAPIProvider.addHook(new ForgePermissionsHook());
    start(plugin, server, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
    TaterAPI api = TaterAPIProvider.get(Platform.FORGE);
    api.setServer(() -> new ForgeServer(minecraftServer));
    TaterAPIProvider.setPrimaryServerType(Platform.FORGE);

    if (TaterAPIProvider.isPrimaryServerType(Platform.FORGE)) {
      // Register listeners
      MinecraftForge.EVENT_BUS.register(this);
      MinecraftForge.EVENT_BUS.register(new ForgeBlockListener());
      MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());
      MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());
      //        MinecraftForge.EVENT_BUS.register(new ForgeServerListener());
    }
  }

  /**
   * Called when the server is stopping.
   *
   * @param event The event.
   */
  @Mod.EventHandler
  public void onServerStopped(FMLServerStoppedEvent event) {
    stop();
  }

  // ----------------------------- Relocated Server listeners -----------------------------

  /**
   * Called when the server starts.
   *
   * @param event The server starting event
   */
  @Mod.EventHandler
  public void onServerStarting(FMLServerStartingEvent event) {
    minecraftServer = event.getServer();
    ServerEvents.STARTING.invoke(new ForgeServerStartingEvent(event));
  }

  /**
   * Called when the server starts.
   *
   * @param event The server started event
   */
  @Mod.EventHandler
  public void onServerStarted2(FMLServerStartedEvent event) {
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
  public void onServerStopped2(FMLServerStoppedEvent event) {
    ServerEvents.STOPPED.invoke(new ForgeServerStoppedEvent(event));
  }
}
