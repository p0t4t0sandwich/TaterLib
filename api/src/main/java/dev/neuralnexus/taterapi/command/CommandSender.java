/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.server.SimpleServer;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@ToBeLibrary("brigadier-general")
public interface CommandSender extends Identifiable, Nameable, Notifiable {
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
    @Nullable Entity getEntity();

    /**
     * Get the player that sent the command
     *
     * @return The player that sent the command
     */
    default @Nullable User getPlayer() {
        if (this.getEntity() instanceof User) {
            return (User) this.getEntity();
        }
        return null;
    }

    /**
     * Get the name of the command sender
     *
     * @return The name of the command sender
     */
    default boolean isPlayer() {
        return this.getEntity() instanceof User;
    }

    /**
     * Get an instance of the server
     *
     * @return The server instance
     */
    default SimpleServer server() {
        return TaterAPI.instance().server();
    }

    @Override
    default UUID uuid() {
        if (this.getEntity() != null) {
            return this.getEntity().uuid();
        }
        return TaterAPI.NIL_UUID;
    }

    @Override
    default void sendMessage(String message) {
        this.getSource().sendMessage(message);
    }
}
