/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.Event;

/** Abstract class for a command register event. */
public interface CommandRegisterEvent extends Event {
    /**
     * Registers a command.
     *
     * @param command The command.
     * @param aliases The aliases of the command.
     */
    void registerCommand(Command command, String... aliases);
}
