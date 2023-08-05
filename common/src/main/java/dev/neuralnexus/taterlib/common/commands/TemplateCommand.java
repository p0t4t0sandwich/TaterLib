package dev.neuralnexus.taterlib.common.commands;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;

public interface TemplateCommand {
    /**
     * Get the name of the command.
     * @return The name of the command.
     */
    String getCommandName();

    /**
     * Get the description of the command.
     * @return The description of the command.
     */
    String getCommandDescription();

    /**
     * Get the usage of the command.
     * @return The usage of the command.
     */
    String getCommandUsage();

    /**
     * Get the permission of the command.
     * @return The permission of the command.
     */
    String getCommandPermission();

    /**
     * Get the subcommand permission.
     * @param subCommand The subcommand.
     * @return The permission.
     */
    default String getCommandPermission(String subCommand) {
        return getCommandPermission() + "." + subCommand;
    }

    /**
     * Execute the command.
     * @param args The arguments of the command.
     * @return The output of the command.
     */
    String executeCommand(String[] args);

    /**
     * Execute the command.
     * @param player The player executing the command.
     * @param args The arguments of the command.
     * @return The output of the command.
     */
    String executeCommand(AbstractPlayer player, String[] args);
}
