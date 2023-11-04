package dev.neuralnexus.taterlib.forge;

import dev.neuralnexus.taterlib.common.Constants;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.forge.event.api.server.ForgeServerStartedEvent;
import dev.neuralnexus.taterlib.forge.event.api.server.ForgeServerStartingEvent;
import dev.neuralnexus.taterlib.forge.event.api.server.ForgeServerStoppedEvent;
import dev.neuralnexus.taterlib.forge.event.api.server.ForgeServerStoppingEvent;
import dev.neuralnexus.taterlib.forge.logger.ForgeLogger;
import dev.neuralnexus.taterlib.forge.command.ForgeTaterLibCommand;
import dev.neuralnexus.taterlib.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.forge.listeners.player.ForgePlayerListener;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;

/**
 * The TaterLib Forge plugin.
 */
@Mod(
        modid = Constants.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions= "*"
)
public class ForgeTaterLibPlugin implements TaterLibPlugin {
    /**
     * Called when the Forge mod is initializing.
     */
    public ForgeTaterLibPlugin() {
        pluginStart(this, new ForgeLogger(LogManager.getLogger()));
        TaterLib.configFolder = "config";
        TaterLib.serverType = "Forge";
        TaterLib.minecraftVersion = ForgeVersion.mcVersion;

        // Register server starting/stopping events
        MinecraftForge.EVENT_BUS.register(this);

        // Register entity event listeners
        MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());

        // Register player event listeners
        MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());

        // Register server event listeners
//        MinecraftForge.EVENT_BUS.register(new ForgeServerListener());
    }

    /**
     * Called when the server is starting.
     * @param event The event.
     */
    @Mod.EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
        // Register LuckPerms hook
        if (Loader.isModLoaded("luckperms")) {
            TaterLib.logger.info("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook("luckperms", new LuckPermsHook());
        }
    }

    /**
     * Registers the TaterLib command.
     * @param event The register commands event.
     */
    @Mod.EventHandler
    public static void registerCommand(FMLServerStartingEvent event) {
        event.registerServerCommand(new ForgeTaterLibCommand());
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    @Mod.EventHandler
    public void onServerStopped(FMLServerStoppedEvent event) {
        pluginStop();
    }


    //----------------------------- Relocated Server listeners -----------------------------

    /**
     * Called when the server starts.
     * @param event The server starting event
     */
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        ServerEvents.STARTING.invoke(new ForgeServerStartingEvent(event));
    }

    /**
     * Called when the server starts.
     * @param event The server started event
     */
    @Mod.EventHandler
    public void onServerStarted2(FMLServerStartedEvent event) {
        ServerEvents.STARTED.invoke(new ForgeServerStartedEvent(event));
    }

    /**
     * Called when the server stops.
     * @param event The server stopping event
     */
    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        ServerEvents.STOPPING.invoke(new ForgeServerStoppingEvent(event));
    }

    /**
     * Called when the server stops.
     * @param event The server stopped event
     */
    @Mod.EventHandler
    public void onServerStopped2(FMLServerStoppedEvent event) {
        ServerEvents.STOPPED.invoke(new ForgeServerStoppedEvent(event));
    }
}
