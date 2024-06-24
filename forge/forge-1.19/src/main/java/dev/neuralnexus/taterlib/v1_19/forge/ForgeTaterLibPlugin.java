package dev.neuralnexus.taterlib.v1_19.forge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_19.forge.hooks.permissions.ForgePermissionsHook;
import dev.neuralnexus.taterlib.v1_19.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.v1_19.forge.listeners.command.ForgeCommandsListener;
import dev.neuralnexus.taterlib.v1_19.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.v1_19.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.v1_19.forge.listeners.server.ForgeServerListener;
import dev.neuralnexus.taterlib.v1_19.vanilla.server.VanillaServer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit(Object plugin, Object server, Object logger) {
        TaterAPIProvider.setPrimaryServerType(Platform.FORGE);
        TaterAPIProvider.addHook(new ForgePermissionsHook());
        start(plugin, server, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(Platform.FORGE);
        api.setServer(() -> new VanillaServer(ServerLifecycleHooks.getCurrentServer()));

        if (TaterAPIProvider.isPrimaryServerType(Platform.FORGE)) {
            // Register listeners
            MinecraftForge.EVENT_BUS.register(this);
            MinecraftForge.EVENT_BUS.register(new ForgeBlockListener());
            MinecraftForge.EVENT_BUS.register(new ForgeCommandsListener());
            MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());
            MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());
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
        stop();
    }
}
