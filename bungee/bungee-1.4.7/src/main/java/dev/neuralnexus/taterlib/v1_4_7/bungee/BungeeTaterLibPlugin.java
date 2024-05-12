package dev.neuralnexus.taterlib.v1_4_7.bungee;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.v1_4_7.bungee.event.command.BungeeCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_4_7.bungee.event.server.BungeeServerStartedEvent;
import dev.neuralnexus.taterlib.v1_4_7.bungee.event.server.BungeeServerStartingEvent;
import dev.neuralnexus.taterlib.v1_4_7.bungee.event.server.BungeeServerStoppedEvent;
import dev.neuralnexus.taterlib.v1_4_7.bungee.event.server.BungeeServerStoppingEvent;
import dev.neuralnexus.taterlib.v1_4_7.bungee.hooks.permissions.BungeePermissionsHook;
import dev.neuralnexus.taterlib.v1_4_7.bungee.listeners.network.BungeePluginMessageListener;
import dev.neuralnexus.taterlib.v1_4_7.bungee.listeners.player.BungeePlayerListener;
import dev.neuralnexus.taterlib.v1_4_7.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.stream.Collectors;

public class BungeeTaterLibPlugin implements TaterLibPlugin {
    private Plugin plugin;

    @Override
    public void platformInit(Object plugin, Object server, Object logger) {
        this.plugin = (Plugin) plugin;
        TaterAPIProvider.addHook(new BungeePermissionsHook());
        pluginStart(
                plugin, server, logger, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.BUNGEECORD);
        api.setPluginList(
                () ->
                        ProxyServer.getInstance().getPluginManager().getPlugins().stream()
                                .map(
                                        p ->
                                                new PluginInfo(
                                                        p.getDescription().getName(),
                                                        p.getDescription().getVersion()))
                                .collect(Collectors.toList()));
        api.setServer(BungeeProxyServer::instance);
    }

    @Override
    public void platformEnable() {
        // Register listeners
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        Utils.runTaskLaterAsync(
                () -> CommandEvents.REGISTER_COMMAND.invoke(new BungeeCommandRegisterEvent()),
                5000L);
        pluginManager.registerListener(plugin, new BungeePlayerListener());
        pluginManager.registerListener(plugin, new BungeePluginMessageListener());
        ServerEvents.STARTING.invoke(new BungeeServerStartingEvent());
        Utils.runTaskLaterAsync(
                () -> ServerEvents.STARTED.invoke(new BungeeServerStartedEvent()), 5000L);
    }

    @Override
    public void platformDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BungeeServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BungeeServerStoppedEvent());
        pluginStop();
    }
}
