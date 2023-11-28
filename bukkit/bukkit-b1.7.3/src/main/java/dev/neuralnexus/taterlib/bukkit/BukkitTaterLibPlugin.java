package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.bukkit.logger.BukkitLogger;
import dev.neuralnexus.taterlib.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitTaterLibPlugin extends JavaPlugin implements TaterLibPlugin {
    private static BukkitTaterLibPlugin instance;

    public BukkitTaterLibPlugin() {
        instance = this;

        TaterAPIProvider.register(getServer().getVersion());
        pluginStart(this, new BukkitLogger(Bukkit.getLogger()));
        TaterAPI api = TaterAPIProvider.get(ServerType.BUKKIT);
        api.setIsPluginLoaded((plugin) -> getServer().getPluginManager().isPluginEnabled(plugin));
        api.setServer(() -> new BukkitServer(getServer()));
    }

    /**
     * Gets the instance of the plugin
     *
     * @return The instance of the plugin
     */
    public static BukkitTaterLibPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());

        // Register listeners
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvent(
                Event.Type.BLOCK_BREAK, new BukkitBlockListener(), Event.Priority.Normal, this);
        getServer()
                .getScheduler()
                .scheduleSyncDelayedTask(
                        this,
                        () ->
                                CommandEvents.REGISTER_COMMAND.invoke(
                                        new BukkitCommandRegisterEvent()),
                        200L);
        pluginManager.registerEvent(
                Event.Type.ENTITY_DAMAGE, new BukkitEntityListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(
                Event.Type.ENTITY_DEATH, new BukkitEntityListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(
                Event.Type.CREATURE_SPAWN, new BukkitEntityListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(
                Event.Type.PLAYER_JOIN, new BukkitPlayerListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(
                Event.Type.PLAYER_QUIT, new BukkitPlayerListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(
                Event.Type.PLAYER_CHAT, new BukkitPlayerListener(), Event.Priority.Highest, this);
        pluginManager.registerEvent(
                Event.Type.PLAYER_RESPAWN, new BukkitPlayerListener(), Event.Priority.Normal, this);
        ServerEvents.STARTING.invoke(new BukkitServerStartingEvent());
        getServer()
                .getScheduler()
                .scheduleSyncDelayedTask(
                        this,
                        () -> ServerEvents.STARTED.invoke(new BukkitServerStartedEvent()),
                        5 * 20L);
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BukkitServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BukkitServerStoppedEvent());
        pluginStop();
    }
}
