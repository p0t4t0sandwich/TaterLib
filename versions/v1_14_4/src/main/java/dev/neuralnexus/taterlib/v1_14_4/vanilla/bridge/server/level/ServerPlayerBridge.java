/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.level;

import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;

public interface ServerPlayerBridge {
    int bridge$ping();

    void bridge$kick(String message);

    void bridge$sendPacket(ResourceKey channel, byte[] data);

    void bridge$setRespawnPosition(Location location, boolean forced);
}
