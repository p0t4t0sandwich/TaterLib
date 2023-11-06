package dev.neuralnexus.taterlib.forge;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.forge.listeners.command.ForgeCommandsListener;
import dev.neuralnexus.taterlib.forge.logger.ForgeLogger;
import dev.neuralnexus.taterlib.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.forge.listeners.server.ForgeServerListener;
import dev.neuralnexus.taterlib.forge.networking.ModMessages;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;

/**
 * The TaterLib Forge plugin.
 */
@Mod(TaterLib.Constants.PROJECT_ID)
public class ForgeTaterLibPlugin implements TaterLibPlugin {
    /**
     * Called when the Forge mod is initializing.
     */
    public ForgeTaterLibPlugin() {
        TaterAPIProvider.register(new TaterAPI.Data(
                "config",
                FMLLoader.versionInfo().mcVersion()
        ));
        pluginStart(this, new ForgeLogger(LogManager.getLogger()));
        TaterAPI api = TaterAPIProvider.get();

        // Register server starting/stopping events
        MinecraftForge.EVENT_BUS.register(this);

        // Register command event listeners
        MinecraftForge.EVENT_BUS.register(new ForgeCommandsListener());

        // Register entity event listeners
        MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());

        // Register player event listeners
        MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());

        // Register server event listeners
        MinecraftForge.EVENT_BUS.register(new ForgeServerListener());

        // Register plugin channels
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        api.setRegisterChannels(ModMessages::addChannels);
    }

    /**
     * Called when CommonSetupEvent is fired.
     * @param event The event.
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
        ModMessages.clearQueue();
    }

    /**
     * Called when the server is starting.
     * @param event The event.
     */
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        // Register LuckPerms hook
        if (ModList.get().isLoaded("luckperms")) {
            TaterLib.getLogger().info("LuckPerms detected, enabling LuckPerms hook.");
            TaterAPIProvider.get().addHook("luckperms", new LuckPermsHook());
        }
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        pluginStop();
    }
}
