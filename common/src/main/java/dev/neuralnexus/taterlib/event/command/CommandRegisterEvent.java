package dev.neuralnexus.taterlib.event.command;

import dev.neuralnexus.taterlib.command.Command;

/** Abstract class for a command register event. */
public interface CommandRegisterEvent {
    /**
     * Registers a command.
     *
     * @param plugin The plugin.
     * @param command The command.
     * @param aliases The aliases of the command.
     */
    void registerCommand(Object plugin, Command command, String... aliases);
}
