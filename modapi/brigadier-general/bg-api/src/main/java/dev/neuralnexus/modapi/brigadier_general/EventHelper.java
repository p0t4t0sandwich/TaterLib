/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier_general;

import com.mojang.brigadier.CommandDispatcher;

import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.command.impl.CommandRegisterEventImpl;

public final class EventHelper {
    @SuppressWarnings("unchecked")
    public static void registerCommand(CommandDispatcher<?> dispatcher) {
        CommandEvents.REGISTER_COMMAND.invoke(
                new CommandRegisterEventImpl((CommandDispatcher<CommandSource>) dispatcher));
        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                new CommandRegisterEventImpl((CommandDispatcher<CommandSource>) dispatcher));
    }
}
