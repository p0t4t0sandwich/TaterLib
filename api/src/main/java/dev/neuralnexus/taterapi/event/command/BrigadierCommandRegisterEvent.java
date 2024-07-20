/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.event.Event;

/** Abstract class for a brigadier command register event. */
public interface BrigadierCommandRegisterEvent<S> extends Event {
    /**
     * Gets if the server is dedicated.
     *
     * @return If the server is dedicated.
     */
    boolean isDedicated();

    /**
     * Gets the command dispatcher.
     *
     * @return The command dispatcher.
     */
    CommandDispatcher<S> dispatcher();

    /**
     * Registers a LiteralCommandNode<S>.
     *
     * @param node The node.
     */
    void registerCommand(LiteralArgumentBuilder<S> node, String commandName, String... aliases);

    /**
     * Get the sender.
     *
     * @param source The source.
     * @return The sender.
     */
    CommandSender getSender(S source);

    /**
     * Get the player.
     *
     * @param source The source.
     * @return The player.
     */
    SimplePlayer getPlayer(S source);

    /**
     * Check if the source is a player.
     *
     * @param source The source.
     */
    boolean isPlayer(S source);
}
