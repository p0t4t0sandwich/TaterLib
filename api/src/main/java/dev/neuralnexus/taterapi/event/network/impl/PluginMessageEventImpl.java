/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network.impl;

import dev.neuralnexus.taterapi.event.network.PluginMessageEvent;
import dev.neuralnexus.taterapi.network.CustomPayload;

/** General implementation of {@link PluginMessageEvent}. */
public class PluginMessageEventImpl implements PluginMessageEvent {
    private final CustomPayload packet;

    public PluginMessageEventImpl(CustomPayload packet) {
        this.packet = packet;
    }

    @Override
    public CustomPayload packet() {
        return this.packet;
    }

    public static class Player extends PluginMessageEventImpl implements PluginMessageEvent.Player {
        private final dev.neuralnexus.taterapi.entity.player.Player player;

        public Player(
                CustomPayload packet, dev.neuralnexus.taterapi.entity.player.SimplePlayer player) {
            super(packet);
            this.player = (dev.neuralnexus.taterapi.entity.player.Player) player;
        }

        @Override
        public dev.neuralnexus.taterapi.entity.player.Player player() {
            return this.player;
        }
    }

    public static class Server extends PluginMessageEventImpl implements PluginMessageEvent.Server {
        private final dev.neuralnexus.taterapi.server.Server server;

        public Server(CustomPayload packet, dev.neuralnexus.taterapi.server.Server server) {
            super(packet);
            this.server = server;
        }

        @Override
        public dev.neuralnexus.taterapi.server.Server server() {
            return this.server;
        }
    }
}
