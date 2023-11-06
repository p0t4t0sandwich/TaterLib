package dev.neuralnexus.taterlib.common.event.command;

import dev.neuralnexus.taterlib.common.command.Command;

/**
 * Abstract class for a command register event.
 */
public interface CommandRegisterEvent {
    /**
     * Registers a command.
     * @param plugin The plugin.
     * @param command The command.
     * @param aliases The aliases of the command.
     */
    void registerCommand(Object plugin, Command command, String... aliases);
}
