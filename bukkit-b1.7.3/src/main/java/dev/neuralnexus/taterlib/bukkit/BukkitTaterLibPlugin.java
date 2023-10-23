package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.bukkit.commands.BukkitTaterLibCommand;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.commands.TaterLibCommand;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
//import org.bukkit.plugin.messaging.Messenger;

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
            TaterLib.addHook(new LuckPermsHook());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {
        PluginManager pluginManager = getServer().getPluginManager();

        // Register player listeners
        pluginManager.registerEvent(Event.Type.PLAYER_JOIN, new BukkitPlayerListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(Event.Type.PLAYER_QUIT, new BukkitPlayerListener(), Event.Priority.Normal, this);
        pluginManager.registerEvent(Event.Type.PLAYER_CHAT, new BukkitPlayerListener(), Event.Priority.Highest, this);

        // Register server listeners
        ServerListener.onServerStarting();
        getServer().getScheduler().scheduleSyncRepeatingTask(this, ServerListener::onServerStarted, 5*20L, 0);
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

        // Register plugin message channels
//        Messenger messenger = getServer().getMessenger();
//        TaterLib.setRegisterChannels((channels) -> channels.forEach((channel) -> {
//            messenger.registerIncomingPluginChannel(this, channel, new BukkitPluginMessageListener());
//            messenger.registerOutgoingPluginChannel(this, channel);
//        }));

        pluginStart();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onDisable() {
        // Run server stopping events
        ServerListener.onServerStopping();
        ServerListener.onServerStopped();
        pluginStop();
    }
}
