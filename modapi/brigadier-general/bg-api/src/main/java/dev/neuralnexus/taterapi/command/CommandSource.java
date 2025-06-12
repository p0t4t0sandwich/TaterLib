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
    /**
     * Get the original source of the command
     *
     * @return The original source of the command
     */
    Notifiable source();

    /**
     * Get the actor/entity that sent the command
     *
     * @return The actor/entity that sent the command
     */
    @Nullable Actor actor();

    /**
     * Get the actor/entity that sent the command
     *
     * @return The actor/entity that sent the command
     */
    default @Nullable Actor entity() {
        return this.actor();
    }

    /**
     * Get the subject/player that sent the command
     *
     * @return The subject/player that sent the command
     */
    default @Nullable Subject subject() {
        if (this.entity() instanceof Subject) {
            return (Subject) this.entity();
        }
        return null;
    }

    /**
     * Get the subject/player that sent the command
     *
     * @return The subject/player that sent the command
     */
    default @Nullable Subject player() {
        return this.subject();
    }

    /**
     * Check if the command was sent by a subject/player
     *
     * @return true if the command was sent by a subject/player
     */
    default boolean isSubject() {
        return this.actor() instanceof Subject;
    }

    /**
     * Check if the command was sent by a subject/player
     *
     * @return true if the command was sent by a subject/player
     */
    default boolean isPlayer() {
        return this.isSubject();
    }

    @Override
    default UUID uuid() {
        Actor entity = this.entity();
        if (entity != null) {
            return entity.uuid();
        }
        return Notifiable.NIL_UUID;
    }

    @Override
    default String name() {
        Actor entity = this.entity();
        if (entity != null) {
            return entity.name();
        }
        return "Server";
    }

    @Override
    default void sendMessage(String message) {
        this.source().sendMessage(message);
    }
}
