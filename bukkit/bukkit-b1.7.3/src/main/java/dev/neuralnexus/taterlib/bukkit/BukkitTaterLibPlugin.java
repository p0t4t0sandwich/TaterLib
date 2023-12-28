package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.bukkit.hooks.permissions.BukkitPermissionsHook;
import dev.neuralnexus.taterlib.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitTaterLibPlugin implements TaterLibPlugin {
    public static JavaPlugin plugin;

    /**
     * Gets the instance of the plugin
     *
     * @return The instance of the plugin
     */
    public static JavaPlugin getInstance() {
        return plugin;
    }

    @Override
    public void platformInit(Object plugin, Object logger) {
        BukkitTaterLibPlugin.plugin = (JavaPlugin) plugin;

        TaterAPIProvider.register(Bukkit.getServer().getVersion());
        TaterAPIProvider.addHook(new BukkitPermissionsHook());
        pluginStart(
                BukkitTaterLibPlugin.plugin,
                new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.BUKKIT);
        api.setIsPluginLoaded(
                (pluginId) -> Bukkit.getServer().getPluginManager().isPluginEnabled(pluginId));
        api.setServer(() -> new BukkitServer(Bukkit.getServer()));
    }

    @Override
    public void platformEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());

        // Register listeners
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvent(
                Event.Type.BLOCK_BREAK, new BukkitBlockListener(), Event.Priority.Normal, plugin);
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
                Event.Type.ENTITY_DEATH, new BukkitEntityListener(), Event.Priority.Normal, plugin);
        pluginManager.registerEvent(
                Event.Type.CREATURE_SPAWN,
                new BukkitEntityListener(),
                Event.Priority.Normal,
                plugin);
        pluginManager.registerEvent(
                Event.Type.PLAYER_JOIN, new BukkitPlayerListener(), Event.Priority.Normal, plugin);
        pluginManager.registerEvent(
                Event.Type.PLAYER_QUIT, new BukkitPlayerListener(), Event.Priority.Normal, plugin);
        pluginManager.registerEvent(
                Event.Type.PLAYER_CHAT, new BukkitPlayerListener(), Event.Priority.Highest, plugin);
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

    @Override
    public void platformDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BukkitServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BukkitServerStoppedEvent());
        pluginStop();
    }
}
