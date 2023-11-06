package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.bukkit.event.api.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.PaperPlayerListener;
import dev.neuralnexus.taterlib.bukkit.listeners.pluginmessages.BukkitPluginMessageListener;
import dev.neuralnexus.taterlib.bukkit.listeners.server.BukkitServerListener;
import dev.neuralnexus.taterlib.bukkit.logger.BukkitLogger;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.api.info.ServerType;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

/**
 * The TaterLib Bukkit plugin.
 */
public class BukkitTaterLibPlugin extends JavaPlugin implements TaterLibPlugin {
    private static BukkitTaterLibPlugin instance;

    /**
     * Gets the instance of the plugin.
     * @return The instance of the plugin.
     */
    public static BukkitTaterLibPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        TaterAPIProvider.register("plugins", getServer().getBukkitVersion());
        pluginStart(this, new BukkitLogger(getLogger()));
        TaterAPI api = TaterAPIProvider.get();
        api.setIsPluginLoaded((plugin) -> getServer().getPluginManager().isPluginEnabled(plugin));

        instance = this;

        // Register command listeners
        getServer().getScheduler().runTaskLater(this, () -> CommandEvents.REGISTER_COMMAND.invoke(new BukkitCommandRegisterEvent()), 200L);

        PluginManager pluginManager = getServer().getPluginManager();

        // Register entity listeners
        pluginManager.registerEvents(new BukkitEntityListener(), this);

        // Register player listeners
        pluginManager.registerEvents(new BukkitPlayerListener(), this);
        if (api.serverType().equals(ServerType.PAPER)) {
            pluginManager.registerEvents(new PaperPlayerListener(), this);
        }

        // Register plugin message channels
        Messenger messenger = getServer().getMessenger();
        api.setRegisterChannels((channels) -> channels.forEach((channel) -> {
            messenger.registerIncomingPluginChannel(this, channel, new BukkitPluginMessageListener());
            messenger.registerOutgoingPluginChannel(this, channel);
        }));

        // Register server listeners
        ServerEvents.STARTING.invoke(new BukkitServerStartingEvent());
        pluginManager.registerEvents(new BukkitServerListener(), this);
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BukkitServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BukkitServerStoppedEvent());
        pluginStop();
    }
}
