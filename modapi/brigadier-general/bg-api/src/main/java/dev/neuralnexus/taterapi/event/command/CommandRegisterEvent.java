/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
