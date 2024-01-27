package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.bukkit.adapters.BukkitAdapters;
import dev.neuralnexus.taterlib.bukkit.adapters.BukkitBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.bukkit.event.pluginmessages.BukkitRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.bukkit.hooks.permissions.BukkitPermissionsHook;
import dev.neuralnexus.taterlib.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.bukkit.listeners.player.PaperPlayerListener;
import dev.neuralnexus.taterlib.bukkit.listeners.server.BukkitServerListener;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.api.PluginMessageEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.vanilla.event.server.VanillaServerStartingEvent;
import dev.neuralnexus.taterlib.vanilla.event.server.VanillaServerStoppedEvent;
import dev.neuralnexus.taterlib.vanilla.event.server.VanillaServerStoppingEvent;
import dev.neuralnexus.taterlib.vanilla.server.VanillaServer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitTaterLibPlugin implements TaterLibPlugin {
    public static JavaPlugin plugin;
    private static boolean hasStarted = false;

    @Override
    public void platformInit(Object plugin, Object logger) {
        BukkitTaterLibPlugin.plugin = (JavaPlugin) plugin;

        TaterAPIProvider.register();
        TaterAPIProvider.addHook(new BukkitPermissionsHook());
        pluginStart(
                BukkitTaterLibPlugin.plugin,
                new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.BUKKIT);
        api.setIsPluginLoaded(
                (pluginId) -> Bukkit.getServer().getPluginManager().isPluginEnabled(pluginId));
        api.setServer(() -> new VanillaServer(BukkitAdapters.getServer()));
    }

    @Override
    public void platformEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());

        // Register listeners
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new BukkitBlockListener(), plugin);
        pluginManager.registerEvents(new BukkitEntityListener(), plugin);
        pluginManager.registerEvents(new BukkitPlayerListener(), plugin);
        if (TaterAPIProvider.serverType().isPaperBased()) {
            pluginManager.registerEvents(new PaperPlayerListener(), plugin);
        }
        if (!hasStarted) {
            hasStarted = true;
            ServerEvents.STARTING.invoke(
                    new VanillaServerStartingEvent(BukkitAdapters.getServer()));
        }
        pluginManager.registerEvents(new BukkitServerListener(), plugin);

        if (TaterAPIProvider.isBrigadierSupported()) {
            Bukkit.getScheduler()
                    .runTaskLater(
                            plugin,
                            () ->
                                    // Register brigadier commands
                                    CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                                            new BukkitBrigadierCommandRegisterEvent(
                                                    BukkitAdapters.getCommandDispatcher())),
                            1);
        }

        Bukkit.getServer()
                .getScheduler()
                .runTaskLater(
                        plugin,
                        () -> {
                            // Register commands
                            CommandEvents.REGISTER_COMMAND.invoke(new BukkitCommandRegisterEvent());

                            // Register plugin messages
                            PluginMessageEvents.REGISTER_PLUGIN_MESSAGES.invoke(
                                    new BukkitRegisterPluginMessagesEvent());
                        },
                        200L);
    }

    @Override
    public void platformDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new VanillaServerStoppingEvent(BukkitAdapters.getServer()));
        ServerEvents.STOPPED.invoke(new VanillaServerStoppedEvent(BukkitAdapters.getServer()));
        pluginStop();
    }
}
