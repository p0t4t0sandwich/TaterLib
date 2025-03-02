/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0;

import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.network.impl.C2SCustomPacketEventImpl;
import dev.neuralnexus.taterapi.event.network.impl.S2PCustomPacketEventImpl;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.network.impl.CustomPayloadPacketImpl;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.command.VelocityBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.command.VelocityCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.network.VelocityRegisterPacketChannelsEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.player.VelocityPlayerLoginEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.player.VelocityPlayerLogoutEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.player.VelocityPlayerMessageEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.player.VelocityPlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityProxyServer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityServer;

import java.time.Duration;

public class VelocityTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPI.instance().setServer(Platforms.VELOCITY, VelocityProxyServer::instance);
    }

    @Override
    public void onEnable() {
        TaterLib.start();
        // Register listeners
        PluginContainer plugin = (PluginContainer) Loader.instance().plugin();
        ProxyServer proxyServer = (ProxyServer) MetaAPI.instance().server();
        EventManager eventManager = proxyServer.getEventManager();

        // Player listeners
        // Note: Player login
        eventManager.register(
                plugin,
                ServerConnectedEvent.class,
                event -> {
                    // If player is switching servers, don't run this function
                    if (event.getPreviousServer().isPresent()) return;
                    PlayerEvents.LOGIN.invoke(new VelocityPlayerLoginEvent(event));
                });
        eventManager.register(
                plugin,
                DisconnectEvent.class,
                event -> PlayerEvents.LOGOUT.invoke(new VelocityPlayerLogoutEvent(event)));
        eventManager.register(
                plugin,
                PlayerChatEvent.class,
                event -> {
                    if (event.getMessage().startsWith("/")) return;
                    PlayerEvents.MESSAGE.invoke(new VelocityPlayerMessageEvent(event));
                });
        // Note: Player server switch
        eventManager.register(
                plugin,
                ServerConnectedEvent.class,
                event -> {
                    // If player is just joining, don't run this function
                    if (event.getPreviousServer().isEmpty()) return;
                    PlayerEvents.SERVER_SWITCH.invoke(new VelocityPlayerServerSwitchEvent(event));
                });

        // Network listeners
        eventManager.register(
                plugin,
                PluginMessageEvent.class,
                event -> {
                    CustomPayloadPacket packet =
                            new CustomPayloadPacketImpl(
                                    ResourceKey.of(event.getIdentifier().getId()), event.getData());
                    if (event.getSource() instanceof Player) {
                        NetworkEvents.C2S_CUSTOM_PACKET.invoke(
                                new C2SCustomPacketEventImpl(
                                        packet,
                                        new VelocityPlayer(
                                                (com.velocitypowered.api.proxy.Player)
                                                        event.getSource())));
                    } else if (event.getSource() instanceof ServerConnection) {
                        NetworkEvents.S2P_CUSTOM_PACKET.invoke(
                                new S2PCustomPacketEventImpl(
                                        packet,
                                        new VelocityServer((RegisteredServer) event.getSource())));
                    }
                });

        // Server listeners
        eventManager.register(
                plugin,
                ProxyInitializeEvent.class,
                event -> ServerEvents.STARTING.invoke(new ServerStartingEvent() {}));
        eventManager.register(
                plugin,
                ProxyShutdownEvent.class,
                event -> ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {}));

        proxyServer
                .getScheduler()
                .buildTask(
                        plugin,
                        () -> {
                            // Register commands
                            CommandEvents.REGISTER_COMMAND.invoke(
                                    new VelocityCommandRegisterEvent());
                            CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                                    new VelocityBrigadierCommandRegisterEvent());

                            // Register plugin messages
                            NetworkEvents.REGISTER_CHANNELS.invoke(
                                    new VelocityRegisterPacketChannelsEvent());

                            // Fire server started event
                            ServerEvents.STARTED.invoke(new ServerStartedEvent() {});
                        })
                .delay(Duration.ofSeconds(5))
                .schedule();
    }

    @Override
    public void onDisable() {
        ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {});
        TaterLib.stop();
    }
}
