package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.bukkit.event.api.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.bukkit.logger.BukkitLogger;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The TaterLib Bukkit plugin.
 */
public class BukkitTaterLibPlugin extends JavaPlugin implements TaterLibPlugin {
    private static BukkitTaterLibPlugin instance;

    /**
     * Gets the instance of the plugin
     * @return The instance of the plugin
     */
    public static BukkitTaterLibPlugin getInstance() {
        return instance;
    }

    public BukkitTaterLibPlugin() {
        instance = this;

        TaterAPIProvider.register("plugins", getServer().getVersion());
        pluginStart(this, new BukkitLogger(Bukkit.getLogger()));
        TaterAPI api = TaterAPIProvider.get();
        api.setIsPluginLoaded((plugin) -> getServer().getPluginManager().isPluginEnabled(plugin));
    }

    @Override
    public void onEnable() {
        // Register command listeners
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> CommandEvents.REGISTER_COMMAND.invoke(new BukkitCommandRegisterEvent()), 200L);

        PluginManager pluginManager = getServer().getPluginManager();

        // Register entity listeners
        pluginManager.registerEvent(Event.Type.ENTITY_DAMAGE, new BukkitEntityListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(Event.Type.ENTITY_DEATH, new BukkitEntityListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(Event.Type.CREATURE_SPAWN, new BukkitEntityListener(), Event.Priority.Normal, this);

        // Register player listeners
        pluginManager.registerEvent(Event.Type.PLAYER_JOIN, new BukkitPlayerListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(Event.Type.PLAYER_QUIT, new BukkitPlayerListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(Event.Type.PLAYER_CHAT, new BukkitPlayerListener(), Event.Priority.Highest, this);
        pluginManager.registerEvent(Event.Type.PLAYER_RESPAWN, new BukkitPlayerListener(), Event.Priority.Normal, this);

        // Register server listeners
        ServerEvents.STARTING.invoke(new BukkitServerStartingEvent());
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> ServerEvents.STARTED.invoke(new BukkitServerStartedEvent()), 5*20L);
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BukkitServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BukkitServerStoppedEvent());
        pluginStop();
    }
}
