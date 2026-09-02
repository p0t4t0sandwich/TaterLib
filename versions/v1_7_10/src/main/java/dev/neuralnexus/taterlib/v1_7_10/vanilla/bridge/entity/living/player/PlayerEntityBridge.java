/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.living.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.world.Location;

public interface PlayerEntityBridge {
    void bridge$setSpawn(Location location, boolean forced);

    GameMode bridge$gameMode();

    void bridge$setGameMode(GameMode gameMode);
}
