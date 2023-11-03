package dev.neuralnexus.taterlib.common.event.command;

import dev.neuralnexus.taterlib.common.command.Sender;

public interface CommandRegisterEvent {
    @FunctionalInterface
    interface CommandCallback {
        void execute(Sender sender, String[] args);
    }

    /**
     * Registers a command.
     * @param name The name of the command
     * @param description The description of the command
     * @param usage The usage of the command
     * @param permission The permission of the command
     * @param aliases The aliases of the command
     */
    void registerCommand(String name, String description, String usage, String permission, String... aliases);
}
