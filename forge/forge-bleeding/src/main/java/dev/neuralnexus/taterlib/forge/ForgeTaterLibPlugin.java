package dev.neuralnexus.taterlib.forge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ModInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.forge.hooks.permissions.ForgePermissionsHook;
import dev.neuralnexus.taterlib.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.forge.listeners.command.ForgeCommandsListener;
import dev.neuralnexus.taterlib.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.forge.listeners.server.ForgeServerListener;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_20_2.forge.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.vanilla.server.VanillaServer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import java.util.stream.Collectors;

public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void platformInit(Object plugin, Object server, Object logger) {
        TaterAPIProvider.addHook(new ForgePermissionsHook());
        pluginStart(
                plugin, server, logger, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.FORGE);
        api.setModList(
                () ->
                        ModList.get().getMods().stream()
                                .map(
                                        modContainer ->
                                                new ModInfo(
                                                        modContainer.getModId(),
                                                        modContainer.getDisplayName(),
                                                        modContainer.getVersion().toString()))
                                .collect(Collectors.toList()));
        api.setServer(VanillaServer::instance);

        // Register listeners
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ForgeBlockListener());
        MinecraftForge.EVENT_BUS.register(new ForgeCommandsListener());
        MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());
        MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());
        MinecraftForge.EVENT_BUS.register(new ForgeServerListener());
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
