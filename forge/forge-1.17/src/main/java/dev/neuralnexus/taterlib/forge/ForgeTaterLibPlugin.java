package dev.neuralnexus.taterlib.forge;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.api.info.ServerType;
import dev.neuralnexus.taterlib.common.event.api.PluginEvents;
import dev.neuralnexus.taterlib.common.event.api.PluginMessageEvents;
import dev.neuralnexus.taterlib.common.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.forge.event.pluginmessage.ForgeRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.forge.listeners.command.ForgeCommandsListener;
import dev.neuralnexus.taterlib.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.forge.listeners.server.ForgeServerListener;
import dev.neuralnexus.taterlib.forge.logger.ForgeLogger;
import dev.neuralnexus.taterlib.forge.networking.ModMessages;
import dev.neuralnexus.taterlib.forge.server.ForgeServer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;
import net.minecraftforge.fmlserverevents.FMLServerStoppedEvent;

import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Field;

/** Forge entry point. */
@Mod(TaterLib.Constants.PROJECT_ID)
public class ForgeTaterLibPlugin implements TaterLibPlugin {
    public ForgeTaterLibPlugin() {
        String minecraftVersion = "";
        try {
            Field mcVersionField = FMLLoader.class.getDeclaredField("mcVersion");
            mcVersionField.setAccessible(true);
            minecraftVersion = (String) mcVersionField.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        TaterAPIProvider.register(minecraftVersion);
        pluginStart(this, new ForgeLogger(LogManager.getLogger()));
        TaterAPI api = TaterAPIProvider.get(ServerType.FORGE);
        api.setIsModLoaded(ModList.get()::isLoaded);
        api.setServer(() -> new ForgeServer(ServerLifecycleHooks.getCurrentServer()));

        // Register listeners
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ForgeBlockListener());
        MinecraftForge.EVENT_BUS.register(new ForgeCommandsListener());
        MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());
        MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());
        MinecraftForge.EVENT_BUS.register(new ForgeServerListener());

        // Register plugin channels
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
    }

    /**
     * Called when CommonSetupEvent is fired.
     *
     * @param event The event.
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());
        PluginMessageEvents.REGISTER_PLUGIN_MESSAGES.invoke(new ForgeRegisterPluginMessagesEvent());
        ModMessages.register();
        ModMessages.clearQueue();
    }

    /**
     * Called when the server is stopping.
     *
     * @param event The event.
     */
    @SubscribeEvent
    public void onServerStopped(FMLServerStoppedEvent event) {
        pluginStop();
    }
}
