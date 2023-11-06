package dev.neuralnexus.taterlib.forge;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.forge.listeners.command.ForgeCommandsListener;
import dev.neuralnexus.taterlib.forge.logger.ForgeLogger;
import dev.neuralnexus.taterlib.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.forge.listeners.server.ForgeServerListener;
import dev.neuralnexus.taterlib.forge.networking.ModMessages;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fmlserverevents.FMLServerStoppedEvent;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Field;

/**
 * The TaterLib Forge plugin.
 */
@Mod(TaterLib.Constants.PROJECT_ID)
public class ForgeTaterLibPlugin implements TaterLibPlugin {
    /**
     * Called when the Forge mod is initializing.
     */
    public ForgeTaterLibPlugin() {
        String minecraftVersion = "";
        try {
            Field mcVersionField = FMLLoader.class.getDeclaredField("mcVersion");
            mcVersionField.setAccessible(true);
            minecraftVersion = (String) mcVersionField.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        TaterAPIProvider.register("config", minecraftVersion);
        pluginStart(this, new ForgeLogger(LogManager.getLogger()));
        TaterAPI api = TaterAPIProvider.get();
        api.setIsPluginLoaded(ModList.get()::isLoaded);
        api.setRegisterChannels(ModMessages::addChannels);

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
    public void onServerStopped(FMLServerStoppedEvent event) {
        pluginStop();
    }
}
