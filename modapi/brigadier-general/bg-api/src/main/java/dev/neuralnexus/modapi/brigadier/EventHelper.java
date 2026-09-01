/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier;

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
