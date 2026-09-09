/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.command;

import dev.neuralnexus.taterapi.Result;
import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Actor;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.ServerAware;
import dev.neuralnexus.taterapi.entity.Subject;

import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.UUID;

// TODO: Implement vanilla via mixins
public interface CommandSource extends DataHolder, Identifiable, Nameable, Notifiable, ServerAware {
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
    default @NonNull UUID uuid() {
        final Result<Value<UUID>> v = this.get(Keys.UUID);
        if (v.isSuccess()) return v.unwrap().get();

        final Actor entity = this.entity();
        if (entity != null) return entity.uuid();
        // Edge case for proxies
        final Subject subject = this.player();
        if (subject != null) return subject.uuid();
        return Notifiable.NIL_UUID;
    }

    @Override
    default @NonNull String name() {
        final Result<Value<String>> v = this.get(Keys.NAME);
        if (v.isSuccess()) return v.unwrap().get();

        final Actor entity = this.entity();
        if (entity != null) return entity.name();
        // Edge case for proxies
        final Subject subject = this.player();
        if (subject != null) return subject.name();
        return this.getClass().getSimpleName();
    }

    @Override
    default void sendMessage(String message) {
        this.source().sendMessage(message);
    }
}
