package dev.neuralnexus.taterlib.forge;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.forge.event.api.command.ForgeCommandRegisterEvent;
import dev.neuralnexus.taterlib.forge.event.api.server.ForgeServerStartedEvent;
import dev.neuralnexus.taterlib.forge.event.api.server.ForgeServerStartingEvent;
import dev.neuralnexus.taterlib.forge.event.api.server.ForgeServerStoppedEvent;
import dev.neuralnexus.taterlib.forge.event.api.server.ForgeServerStoppingEvent;
import dev.neuralnexus.taterlib.forge.logger.ForgeLogger;
import dev.neuralnexus.taterlib.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.forge.server.ForgeServer;
import net.minecraft.server.MinecraftServer;
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
        modid = TaterLib.Constants.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions= "*"
)
public class ForgeTaterLibPlugin implements TaterLibPlugin {
    private MinecraftServer server;

    /**
     * Called when the Forge mod is initializing.
     */
    public ForgeTaterLibPlugin() {
        TaterAPIProvider.register("config", ForgeVersion.mcVersion);
        pluginStart(this, new ForgeLogger(LogManager.getLogger()));
        TaterAPI api = TaterAPIProvider.get();
        api.setIsPluginLoaded(Loader::isModLoaded);
        api.setServer(() -> new ForgeServer(server));

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
     * Registers the TaterLib command.
     * @param event The register commands event.
     */
    @Mod.EventHandler
    public static void registerCommand(FMLServerStartingEvent event) {
        CommandEvents.REGISTER_COMMAND.invoke(new ForgeCommandRegisterEvent(event));
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
        server = event.getServer();
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
