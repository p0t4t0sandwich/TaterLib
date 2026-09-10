/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.player.GameMode;

import org.jspecify.annotations.NonNull;

/** The interface for a HumanEntity */
public interface HumanEntity
        extends LivingEntity,
                Damageable,
                DataHolder,
                Identifiable,
                InventoryHolder,
                Locatable,
                Nameable {
    /**
     * Get the player's current gamemode
     *
     * @return The player's current gamemode
     */
    default Value<GameMode> gameMode() {
        return this.getOrThrow(Keys.GAME_MODE);
    }

    /**
     * Set the player's game mode
     *
     * @param id The id of the game mode to set
     */
    default void setGameMode(final int id) {
        this.gameMode().set(GameMode.fromId(id));
    }

    /**
     * Set the player's game mode
     *
     * @param name The name of the game mode to set
     */
    default void setGameMode(final @NonNull String name) {
        this.gameMode().set(GameMode.fromName(name));
    }
}
