package dev.neuralnexus.taterlib.b1_7_3.bukkit;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.hooks.permissions.BukkitPermissionsHook;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
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
        api.setModLoaderVersion(() -> Bukkit.getServer().getVersion());
        api.setPluginList(
                () ->
                        Arrays.stream(Bukkit.getServer().getPluginManager().getPlugins())
                                .map(
                                        p ->
                                                new PluginInfo(
                                                        p.getDescription().getName(),
                                                        p.getDescription().getVersion()))
                                .collect(Collectors.toList()));
        api.setServer(() -> new BukkitServer(Bukkit.getServer()));
        TaterAPIProvider.setPrimaryServerType(ServerType.BUKKIT);
    }

    @Override
    public void platformEnable() {
        if (TaterAPIProvider.isPrimaryServerType(ServerType.BUKKIT)) {
            // Register listeners
            PluginManager pluginManager = Bukkit.getServer().getPluginManager();
            pluginManager.registerEvent(
                    Event.Type.BLOCK_BREAK,
                    new BukkitBlockListener(),
                    Event.Priority.Normal,
                    plugin);
            Bukkit.getServer()
                    .getScheduler()
                    .scheduleSyncDelayedTask(
                            plugin,
                            () ->
                                    CommandEvents.REGISTER_COMMAND.invoke(
                                            new BukkitCommandRegisterEvent()),
                            200L);
            pluginManager.registerEvent(
                    Event.Type.ENTITY_DAMAGE,
                    new BukkitEntityListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.ENTITY_DEATH,
                    new BukkitEntityListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.CREATURE_SPAWN,
                    new BukkitEntityListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.PLAYER_JOIN,
                    new BukkitPlayerListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.PLAYER_QUIT,
                    new BukkitPlayerListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.PLAYER_CHAT,
                    new BukkitPlayerListener(),
                    Event.Priority.Highest,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.PLAYER_RESPAWN,
                    new BukkitPlayerListener(),
                    Event.Priority.Normal,
                    plugin);
            ServerEvents.STARTING.invoke(new BukkitServerStartingEvent());
            Bukkit.getServer()
                    .getScheduler()
                    .scheduleSyncDelayedTask(
                            plugin,
                            () -> ServerEvents.STARTED.invoke(new BukkitServerStartedEvent()),
                            5 * 20L);
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
