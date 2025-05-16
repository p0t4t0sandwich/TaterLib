/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.command;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;

@ToBeLibrary("brigadier-general")
public interface Command {
    /**
     * Get the name of the command.
     *
     * @return The name of the command.
     */
    String name();

    /**
     * Set the name of the command.
     *
     * @param name The name of the command.
     */
    void setName(String name);

    /**
     * Get the description of the command.
     *
     * @return The description of the command.
     */
    String description();

    /**
     * Get the usage of the command.
     *
     * @return The usage of the command.
     */
    String usage();

    /**
     * Get the permission of the command.
     *
     * @return The permission of the command.
     */
    String permission();

    /**
     * Get the subcommand permission.
     *
     * @param subCommand The subcommand.
     * @return The permission.
     */
    default String permission(String subCommand) {
        return permission() + "." + subCommand;
    }

    /**
     * Get the subcommand permission.
     *
     * @param subCommands The subcommands.
     * @return The permission.
     */
    default String permission(String[] subCommands) {
        StringBuilder permission = new StringBuilder(permission());
        for (String subCommand : subCommands) {
            permission.append(".").append(subCommand);
        }
        return permission.toString();
    }

    /**
     * Execute the command.
     *
     * @param args The arguments of the command.
     * @return The output of the command.
     */
    default String execute(String[] args) {
        return null;
    }

    /**
     * Execute the command.
     *
     * @param commandSender The sender of the command.
     * @param label The label of the command.
     * @param args The arguments of the command.
     * @return The output of the command.
     */
    boolean execute(CommandSource commandSender, String label, String[] args);

    @FunctionalInterface
    interface Callback {
        boolean execute(CommandSource commandSender, String label, String[] args);
    }
}
