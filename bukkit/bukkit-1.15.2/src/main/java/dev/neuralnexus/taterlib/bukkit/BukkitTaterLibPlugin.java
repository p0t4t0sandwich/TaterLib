package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.bukkit.event.network.BukkitRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.bukkit.hooks.permissions.BukkitPermissionsHook;
import dev.neuralnexus.taterlib.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.PaperPlayerListener;
import dev.neuralnexus.taterlib.bukkit.listeners.server.BukkitServerListener;
import dev.neuralnexus.taterlib.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BukkitTaterLibPlugin implements TaterLibPlugin {
    public static JavaPlugin plugin;

    @Override
    public void platformInit(Object plugin, Object server, Object logger) {
        BukkitTaterLibPlugin.plugin = (JavaPlugin) plugin;
        TaterAPIProvider.addHook(new BukkitPermissionsHook());
        pluginStart(
                plugin, server, logger, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.BUKKIT);
        api.setPluginList(
                () ->
                        Arrays.stream(Bukkit.getServer().getPluginManager().getPlugins())
                                .map(
                                        p ->
                                                new PluginInfo(
                                                        p.getName(),
                                                        p.getDescription().getVersion()))
                                .collect(Collectors.toList()));
        api.setServer(() -> new BukkitServer(Bukkit.getServer()));
        TaterAPIProvider.setPrimaryServerType(ServerType.BUKKIT);
    }

    @Override
    public void platformEnable() {
        if (TaterAPIProvider.isPrimaryServerType(ServerType.BUKKIT)) {
            // Register listeners
            PluginManager pluginManager = plugin.getServer().getPluginManager();
            pluginManager.registerEvents(new BukkitBlockListener(), plugin);
            pluginManager.registerEvents(new BukkitEntityListener(), plugin);
            pluginManager.registerEvents(new BukkitPlayerListener(), plugin);
            if (TaterAPIProvider.serverType().isPaperBased()) {
                pluginManager.registerEvents(new PaperPlayerListener(), plugin);
            }
            ServerEvents.STARTING.invoke(new BukkitServerStartingEvent());
            pluginManager.registerEvents(new BukkitServerListener(), plugin);

            Bukkit.getServer()
                    .getScheduler()
                    .runTaskLater(
                            plugin,
                            () -> {
                                // Register commands
                                CommandEvents.REGISTER_COMMAND.invoke(
                                        new BukkitCommandRegisterEvent());

                                // Register plugin messages
                                NetworkEvents.REGISTER_PLUGIN_MESSAGES.invoke(
                                        new BukkitRegisterPluginMessagesEvent());
                            },
                            200L);
        }
    }

    @Override
    public void platformDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BukkitServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BukkitServerStoppedEvent());
        pluginStop();
    }
}
