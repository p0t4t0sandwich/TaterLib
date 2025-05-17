/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.command;

import dev.neuralnexus.taterapi.entity.Actor;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.ServerAware;
import dev.neuralnexus.taterapi.entity.player.Subject;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface CommandSource extends Identifiable, Nameable, Notifiable, ServerAware {
    UUID NIL_UUID = new UUID(0, 0);

    /**
     * Get the original source of the command
     *
     * @return The original source of the command
     */
    Notifiable getSource();

    /**
     * Get the entity that sent the command
     *
     * @return The entity that sent the command
     */
    @Nullable Actor getEntity();

    /**
     * Get the player that sent the command
     *
     * @return The player that sent the command
     */
    default @Nullable Subject getPlayer() {
        if (this.getEntity() instanceof Subject) {
            return (Subject) this.getEntity();
        }
        return null;
    }

    /**
     * Get the name of the command sender
     *
     * @return The name of the command sender
     */
    default boolean isPlayer() {
        return this.getEntity() instanceof Subject;
    }

    @Override
    default UUID uuid() {
        if (this.getEntity() != null) {
            return this.getEntity().uuid();
        }
        return NIL_UUID;
    }

    @Override
    default String name() {
        if (this.getEntity() != null) {
            return this.getEntity().name();
        }
        return "Server";
    }

    @Override
    default void sendMessage(String message) {
        this.getSource().sendMessage(message);
    }
}
