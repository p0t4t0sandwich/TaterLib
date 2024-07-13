/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.event.network;

import com.velocitypowered.api.proxy.server.RegisteredServer;

import dev.neuralnexus.taterapi.event.network.PluginMessageEvent;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityServer;

/** Velocity implementation of {@link PluginMessageEvent}. */
public class VelocityPluginMessageEvent implements PluginMessageEvent {
    private final com.velocitypowered.api.event.connection.PluginMessageEvent event;

    public VelocityPluginMessageEvent(
            com.velocitypowered.api.event.connection.PluginMessageEvent event) {
        this.event = event;
    }

    @Override
    public ResourceKey channel() {
        return ResourceKey.of(event.getIdentifier().getId());
    }

    @Override
    public byte[] data() {
        return event.getData();
    }

    /** Velocity implementation of {@link PluginMessageEvent.Player}. */
    public static class Player extends VelocityPluginMessageEvent
            implements PluginMessageEvent.Player {
        private final com.velocitypowered.api.event.connection.PluginMessageEvent event;

        public Player(com.velocitypowered.api.event.connection.PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

            @Override
        public ProxyPlayer player() {
            return new VelocityPlayer((com.velocitypowered.api.proxy.Player) event.getSource());
        }
    }

    /** Velocity implementation of {@link PluginMessageEvent.Server}. */
    public static class Server extends VelocityPluginMessageEvent
            implements PluginMessageEvent.Server {
        private final com.velocitypowered.api.event.connection.PluginMessageEvent event;

        public Server(com.velocitypowered.api.event.connection.PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

            @Override
        public dev.neuralnexus.taterapi.server.Server server() {
            return new VelocityServer((RegisteredServer) event.getSource());
        }
    }
}
