package dev.neuralnexus.taterlib.v1_20_2.forge;

import com.mojang.logging.LogUtils;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_20.forge.hooks.permissions.ForgePermissionsHook;
import dev.neuralnexus.taterlib.v1_20.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.v1_20.forge.listeners.command.ForgeCommandsListener;
import dev.neuralnexus.taterlib.v1_20.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.v1_20.forge.listeners.player.ForgePlayerListener_1_20;
import dev.neuralnexus.taterlib.v1_20.forge.listeners.server.ForgeServerListener;
import dev.neuralnexus.taterlib.v1_20.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_20_2.forge.listeners.player.ForgePlayerListener_1_20_2;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void platformInit(Object plugin, Object logger) {
        TaterAPIProvider.addHook(new ForgePermissionsHook());
        pluginStart(plugin, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, LogUtils.getLogger()));
        TaterAPI api = TaterAPIProvider.get(ServerType.FORGE);
        api.setIsModLoaded(ModList.get()::isLoaded);
        api.setServer(VanillaServer::getInstance);

        if (!TaterAPIProvider.areEventListenersRegistered()) {
            TaterAPIProvider.setEventListenersRegistered(true);
            // Register listeners
            MinecraftForge.EVENT_BUS.register(this);
            MinecraftForge.EVENT_BUS.register(new ForgeBlockListener());
            MinecraftForge.EVENT_BUS.register(new ForgeCommandsListener());
            MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());
            if (TaterAPIProvider.minecraftVersion()
                    .isInRange(true, MinecraftVersion.V1_20, false, MinecraftVersion.V1_20_2)) {
                MinecraftForge.EVENT_BUS.register(new ForgePlayerListener_1_20());
            } else if (TaterAPIProvider.minecraftVersion().isAtLeast(MinecraftVersion.V1_20_2)) {
                MinecraftForge.EVENT_BUS.register(new ForgePlayerListener_1_20_2());
            }
            MinecraftForge.EVENT_BUS.register(new ForgeServerListener());
        }
    }

    /**
     * Called when the server is stopping.
     *
     * @param event The event.
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        pluginStop();
    }
}
