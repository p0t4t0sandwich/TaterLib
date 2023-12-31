package dev.neuralnexus.taterlib.neoforge;

import com.mojang.logging.LogUtils;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.neoforge.listeners.block.NeoForgeBlockListener;
import dev.neuralnexus.taterlib.neoforge.listeners.command.NeoForgeCommandsListener;
import dev.neuralnexus.taterlib.neoforge.logger.NeoForgeLogger;
import dev.neuralnexus.taterlib.neoforge.listeners.entity.NeoForgeEntityListener;
import dev.neuralnexus.taterlib.neoforge.listeners.player.NeoForgePlayerListener;
import dev.neuralnexus.taterlib.neoforge.listeners.server.NeoForgeServerListener;
import dev.neuralnexus.taterlib.neoforge.networking.ModMessages;
import dev.neuralnexus.taterlib.neoforge.server.NeoForgeServer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

/**
 * The TaterLib NeoForge plugin.
 */
@Mod(TaterLib.Constants.PROJECT_ID)
public class NeoForgeTaterLibPlugin implements TaterLibPlugin {
    /**
     * Called when the Forge mod is initializing.
     */
    public NeoForgeTaterLibPlugin() {
        TaterAPIProvider.register("config", FMLLoader.versionInfo().mcVersion());
        pluginStart(this, new NeoForgeLogger(LogUtils.getLogger()));
        TaterAPI api = TaterAPIProvider.get();
        api.setIsPluginLoaded(ModList.get()::isLoaded);
        api.setRegisterChannels(ModMessages::addChannels);
        api.setServer(() -> new NeoForgeServer(ServerLifecycleHooks.getCurrentServer()));

        // Register server starting/stopping events
        NeoForge.EVENT_BUS.register(this);

        // Register block event listeners
        NeoForge.EVENT_BUS.register(new NeoForgeBlockListener());

        // Register command event listeners
        NeoForge.EVENT_BUS.register(new NeoForgeCommandsListener());

        // Register entity event listeners
        NeoForge.EVENT_BUS.register(new NeoForgeEntityListener());

        // Register player event listeners
        NeoForge.EVENT_BUS.register(new NeoForgePlayerListener());

        // Register server event listeners
        NeoForge.EVENT_BUS.register(new NeoForgeServerListener());

        // Register plugin channels
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
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
     * Called when the server is stopping.
     * @param event The event.
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        pluginStop();
    }
}
