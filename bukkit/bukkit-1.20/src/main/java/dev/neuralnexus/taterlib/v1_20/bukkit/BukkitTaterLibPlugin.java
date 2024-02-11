package dev.neuralnexus.taterlib.v1_20.bukkit;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_20.bukkit.adapters.BukkitAdapter;
import dev.neuralnexus.taterlib.v1_20.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_20.bukkit.event.networking.BukkitRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.v1_20.bukkit.hooks.permissions.BukkitPermissionsHook;
import dev.neuralnexus.taterlib.v1_20.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.v1_20.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.v1_20.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.v1_20.bukkit.listeners.player.PaperPlayerAdvancementListener;
import dev.neuralnexus.taterlib.v1_20.bukkit.listeners.server.BukkitServerListener;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.server.VanillaServerStartingEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.server.VanillaServerStoppedEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.server.VanillaServerStoppingEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.server.VanillaServer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitTaterLibPlugin implements TaterLibPlugin {
    public static JavaPlugin plugin;
    private static boolean hasStarted = false;

    @Override
    public void platformInit(Object plugin, Object logger) {
        BukkitTaterLibPlugin.plugin = (JavaPlugin) plugin;
        TaterAPIProvider.addHook(new BukkitPermissionsHook());
        pluginStart(
                BukkitTaterLibPlugin.plugin,
                new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.BUKKIT);
        api.setIsPluginLoaded(
                (pluginId) -> Bukkit.getServer().getPluginManager().isPluginEnabled(pluginId));
        api.setServer(VanillaServer::getInstance);
    }

    @Override
    public void platformEnable() {
        if (!TaterAPIProvider.areEventListenersRegistered()) {
            TaterAPIProvider.setEventListenersRegistered(true);
            // Register listeners
            PluginManager pluginManager = plugin.getServer().getPluginManager();
            pluginManager.registerEvents(new BukkitBlockListener(), plugin);
            pluginManager.registerEvents(new BukkitEntityListener(), plugin);
            pluginManager.registerEvents(new BukkitPlayerListener(), plugin);
            if (TaterAPIProvider.serverType().isPaperBased()) {
                pluginManager.registerEvents(new PaperPlayerAdvancementListener(), plugin);
            }
            if (!hasStarted) {
                hasStarted = true;
                ServerEvents.STARTING.invoke(
                        new VanillaServerStartingEvent(BukkitAdapter.get().getServer()));
            }
            pluginManager.registerEvents(new BukkitServerListener(), plugin);

            Bukkit.getServer()
                    .getScheduler()
                    .runTaskLater(
                            plugin,
                            () -> {
                                // Register commands
                                CommandEvents.REGISTER_COMMAND.invoke(
                                        new BukkitCommandRegisterEvent());

                                if (TaterAPIProvider.isBrigadierSupported()) {
                                    // Register Brigadier commands
                                    CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                                            new VanillaBrigadierCommandRegisterEvent(
                                                    BukkitAdapter.get().getCommandDispatcher(),
                                                    BukkitAdapter.get().getCommandSelection()));
                                }

                                // Register plugin messages
                                NetworkEvents.REGISTER_PLUGIN_MESSAGES.invoke(
                                        new BukkitRegisterPluginMessagesEvent());
                            },
                            200L);
        }
    }

    @Override
    public void platformDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new VanillaServerStoppingEvent(VanillaServer.getServer()));
        ServerEvents.STOPPED.invoke(new VanillaServerStoppedEvent(VanillaServer.getServer()));
        pluginStop();
    }
}
