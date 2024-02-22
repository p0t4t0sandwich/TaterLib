package dev.neuralnexus.taterlib.forge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ModInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.forge.event.command.ForgeCommandRegisterEvent;
import dev.neuralnexus.taterlib.forge.event.server.ForgeServerStartedEvent;
import dev.neuralnexus.taterlib.forge.event.server.ForgeServerStartingEvent;
import dev.neuralnexus.taterlib.forge.event.server.ForgeServerStoppedEvent;
import dev.neuralnexus.taterlib.forge.event.server.ForgeServerStoppingEvent;
import dev.neuralnexus.taterlib.forge.hooks.permissions.ForgePermissionsHook;
import dev.neuralnexus.taterlib.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

import java.util.stream.Collectors;

/** Forge entry point. */
// @Mod(
//        modid = TaterLib.Constants.PROJECT_ID,
//        useMetadata = true,
//        serverSideOnly = true,
//        acceptableRemoteVersions = "*")
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
    public void platformInit(Object plugin, Object server, Object logger) {
        TaterAPIProvider.addHook(new ForgePermissionsHook());
        pluginStart(
                plugin, server, logger, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.FORGE);
        api.setModList(
                () ->
                        Loader.instance().getModList().stream()
                                .map(
                                        modContainer ->
                                                new ModInfo(
                                                        modContainer.getModId(),
                                                        modContainer.getName(),
                                                        modContainer.getVersion()))
                                .collect(Collectors.toList()));
        api.setServer(() -> new ForgeServer(minecraftServer));
        TaterAPIProvider.setPrimaryServerType(ServerType.FORGE);

        if (TaterAPIProvider.isPrimaryServerType(ServerType.FORGE)) {
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
        pluginStop();
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
