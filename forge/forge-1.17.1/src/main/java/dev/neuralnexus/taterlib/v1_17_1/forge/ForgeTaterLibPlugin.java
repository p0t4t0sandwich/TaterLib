package dev.neuralnexus.taterlib.v1_17_1.forge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.utils.forge.modern.FMLAdapters;
import dev.neuralnexus.taterlib.v1_17.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_17_1.forge.hooks.permissions.ForgePermissionsHook;
import dev.neuralnexus.taterlib.v1_17_1.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.v1_17_1.forge.listeners.command.ForgeCommandsListener;
import dev.neuralnexus.taterlib.v1_17_1.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.v1_17_1.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.v1_17_1.forge.listeners.server.ForgeServerListener;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;
import net.minecraftforge.fmlserverevents.FMLServerStoppedEvent;

import org.apache.logging.log4j.LogManager;

public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void platformInit(Object plugin, Object server, Object logger) {
        TaterAPIProvider.setPrimaryServerType(ServerType.FORGE);
        TaterAPIProvider.addHook(new ForgePermissionsHook());
        pluginStart(
                plugin,
                server,
                logger,
                new LoggerAdapter(TaterLib.Constants.PROJECT_ID, LogManager.getLogger()));
        TaterAPI api = TaterAPIProvider.get(ServerType.FORGE);
        api.setModList(() -> FMLAdapters.adaptModList(ModList.get()));
        api.setServer(() -> new VanillaServer(ServerLifecycleHooks.getCurrentServer()));

        if (TaterAPIProvider.isPrimaryServerType(ServerType.FORGE)) {
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
    public void onServerStopped(FMLServerStoppedEvent event) {
        pluginStop();
    }
}
