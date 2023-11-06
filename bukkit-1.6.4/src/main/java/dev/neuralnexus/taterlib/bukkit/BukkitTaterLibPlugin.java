package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.bukkit.event.api.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.bukkit.listeners.pluginmessages.BukkitPluginMessageListener;
import dev.neuralnexus.taterlib.bukkit.logger.BukkitLogger;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.Utils;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

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

    @Override
    public void onEnable() {
        instance = this;
        pluginStart(this, new BukkitLogger(getLogger()));
        TaterLib.configFolder = "plugins";
        TaterLib.serverType = Utils.getBukkitServerType();
        TaterLib.minecraftVersion = getServer().getVersion();

        // Register LuckPerms hook
        if (getServer().getPluginManager().getPlugin("LuckPerms") != null) {
            TaterLib.logger.info("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook("luckperms", new LuckPermsHook());
        }

        // Register command listeners
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> CommandEvents.REGISTER_COMMAND.invoke(new BukkitCommandRegisterEvent()), 200L);

        PluginManager pluginManager = getServer().getPluginManager();

        // Register entity listeners
        pluginManager.registerEvents(new BukkitEntityListener(), this);

        // Register player listeners
        pluginManager.registerEvents(new BukkitPlayerListener(), this);

        // Register plugin message channels
        Messenger messenger = getServer().getMessenger();
        TaterLib.setRegisterChannels((channels) -> channels.forEach((channel) -> {
            messenger.registerIncomingPluginChannel(this, channel, new BukkitPluginMessageListener());
            messenger.registerOutgoingPluginChannel(this, channel);
        }));

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
