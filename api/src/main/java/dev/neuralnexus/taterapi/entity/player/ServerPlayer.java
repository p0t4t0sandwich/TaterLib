/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.entity.player;

import dev.neuralnexus.modapi.metadata.annotations.Range;
import dev.neuralnexus.modapi.metadata.annotations.VersionFeature;
import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.world.Location;

/** The interface for a ServerPlayer */
public interface ServerPlayer extends Player, Connection {
    /**
     * Set the player's spawn point
     *
     * @param location The location to set the spawn point to
     */
    @VersionFeature(name = "ServerPlayer#setSpawn(Location,boolean)", incompatible = @Range(MinecraftVersion.B1_7_3))
    void setSpawn(Location location, boolean forced);

    /**
     * Set the player's spawn point
     *
     * @param location The location to set the spawn point to
     */
    default void setSpawn(Location location) {
        setSpawn(location, false);
    }
}
