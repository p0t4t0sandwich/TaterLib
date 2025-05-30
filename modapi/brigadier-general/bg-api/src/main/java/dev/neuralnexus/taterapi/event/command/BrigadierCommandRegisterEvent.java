/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

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
     * Registers a LiteralCommandNode
     *
     * @param node The node to register
     * @param commandName The name of the command
     * @param aliases The aliases of the command
     */
    void registerCommand(LiteralArgumentBuilder<S> node, String commandName, String... aliases);
}
