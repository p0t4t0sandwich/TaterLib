/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.level;

import dev.neuralnexus.taterapi.network.protocol.Packet;
import dev.neuralnexus.taterapi.world.Location;

import org.jspecify.annotations.NonNull;

public interface ServerPlayerBridge {
    int bridge$ping();

    void bridge$kick(String message);

    void bridge$sendPacket(final @NonNull Packet packet);

    void bridge$setRespawnPosition(Location location, boolean forced);
}
