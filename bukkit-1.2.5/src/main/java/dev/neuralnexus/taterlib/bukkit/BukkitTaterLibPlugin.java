package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.bukkit.event.pluginmessages.BukkitRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.bukkit.event.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.bukkit.logger.BukkitLogger;
import dev.neuralnexus.taterlib.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.api.info.ServerType;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.PluginEvents;
import dev.neuralnexus.taterlib.common.event.api.PluginMessageEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.event.plugin.CommonPluginEnableEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Bukkit entry point.
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

        TaterAPIProvider.register(getServer().getBukkitVersion());
        pluginStart(this, new BukkitLogger(getLogger()));
        TaterAPI api = TaterAPIProvider.get(ServerType.BUKKIT);
        api.setIsPluginLoaded((plugin) -> getServer().getPluginManager().isPluginEnabled(plugin));
        api.setServer(() -> new BukkitServer(getServer()));
    }

    @Override
    public void onEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());

        // Register listeners
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BukkitBlockListener(), this);
        pluginManager.registerEvents(new BukkitEntityListener(), this);
        pluginManager.registerEvents(new BukkitPlayerListener(), this);
        ServerEvents.STARTING.invoke(new BukkitServerStartingEvent());
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> ServerEvents.STARTED.invoke(new BukkitServerStartedEvent()), 5*20L);

        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            // Register commands
            CommandEvents.REGISTER_COMMAND.invoke(new BukkitCommandRegisterEvent());

            // Register plugin messages
            PluginMessageEvents.REGISTER_PLUGIN_MESSAGES.invoke(new BukkitRegisterPluginMessagesEvent());
        }, 200L);
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BukkitServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BukkitServerStoppedEvent());
        pluginStop();
    }
}
