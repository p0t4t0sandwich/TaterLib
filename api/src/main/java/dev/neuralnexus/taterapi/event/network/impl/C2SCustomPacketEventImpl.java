/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network.impl;

import dev.neuralnexus.taterapi.event.network.C2SCustomPacketEvent;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;

/** General implementation of {@link C2SCustomPacketEvent}. */
public class C2SCustomPacketEventImpl implements C2SCustomPacketEvent {
    private final CustomPayloadPacket packet;

    public C2SCustomPacketEventImpl(CustomPayloadPacket packet) {
        this.packet = packet;
    }

    @Override
    public CustomPayloadPacket packet() {
        return this.packet;
    }

    public static class Player extends C2SCustomPacketEventImpl implements C2SCustomPacketEvent.Player {
        private final dev.neuralnexus.taterapi.entity.player.Player player;

        public Player(
                CustomPayloadPacket packet, dev.neuralnexus.taterapi.entity.player.SimplePlayer player) {
            super(packet);
            this.player = (dev.neuralnexus.taterapi.entity.player.Player) player;
        }

        @Override
        public dev.neuralnexus.taterapi.entity.player.Player player() {
            return this.player;
        }
    }

    public static class Server extends C2SCustomPacketEventImpl implements C2SCustomPacketEvent.Server {
        private final dev.neuralnexus.taterapi.server.Server server;

        public Server(CustomPayloadPacket packet, dev.neuralnexus.taterapi.server.Server server) {
            super(packet);
            this.server = server;
        }

        @Override
        public dev.neuralnexus.taterapi.server.Server server() {
            return this.server;
        }
    }
}
