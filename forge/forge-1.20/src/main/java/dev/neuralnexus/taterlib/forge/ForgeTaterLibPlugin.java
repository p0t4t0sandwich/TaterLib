package dev.neuralnexus.taterlib.forge;

import com.mojang.logging.LogUtils;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.forge.hooks.permissions.ForgePermissionsHook;
import dev.neuralnexus.taterlib.forge.listeners.command.ForgeCommandsListener;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.vanilla.server.VanillaServer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.server.ServerLifecycleHooks;

public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void platformInit(Object plugin, Object logger) {
        TaterAPIProvider.register(FMLLoader.versionInfo().mcVersion());
        TaterAPIProvider.addHook(new ForgePermissionsHook());
        pluginStart(this, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, LogUtils.getLogger()));
        TaterAPI api = TaterAPIProvider.get(ServerType.FORGE);
        api.setIsModLoaded(ModList.get()::isLoaded);
        api.setServer(VanillaServer::getInstance);

        // Register listeners
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ForgeCommandsListener());
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
