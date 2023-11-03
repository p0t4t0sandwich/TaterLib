package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.bukkit.commands.BukkitTaterLibCommand;
import dev.neuralnexus.taterlib.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.command.TaterLibCommand;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;

/**
 * The TaterLib Bukkit plugin.
 */
public class BukkitTaterLibPlugin extends TemplateBukkitPlugin implements TaterLibPlugin {
    private static BukkitTaterLibPlugin instance;

    /**
     * Gets the instance of the plugin
     * @return The instance of the plugin
     */
    public static BukkitTaterLibPlugin getInstance() {
        return instance;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerHooks() {
        // Register LuckPerms hook
        if (getServer().getPluginManager().getPlugin("LuckPerms") != null) {
            useLogger("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook("luckperms", new LuckPermsHook());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {
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

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {
        getCommand(TaterLibCommand.getCommandName()).setExecutor(new BukkitTaterLibCommand());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onEnable() {
        instance = this;
        pluginStart();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BukkitServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BukkitServerStoppedEvent());
        pluginStop();
    }
}
