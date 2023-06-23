package dev.neuralnexus.taterapi.common.commands;

public interface TemplateCommand {
    String commandName = "template";
    String commandDescription = "Template command description.";
    String commandUsage = "Template command usage.";
    String commandPermission = "taterapi.template";

    /**
     * Get the name of the command.
     * @return The name of the command.
     */
    default String getCommandName() {
        return commandName;
    }

    /**
     * Get the description of the command.
     * @return The description of the command.
     */
    default String getCommandDescription() {
        return commandDescription;
    }

    /**
     * Get the usage of the command.
     * @return The usage of the command.
     */
    default String getCommandUsage() {
        return commandUsage;
    }

    /**
     * Get the permission of the command.
     * @return The permission of the command.
     */
    default String getCommandPermission() {
        return commandPermission;
    }

    /**
     * Get the subcommand permission.
     * @param subCommand The subcommand.
     * @return The permission.
     */
    default String getCommandPermission(String subCommand) {
        return commandPermission + "." + subCommand;
    }

    /**
     * Execute the command.
     * @param args The arguments of the command.
     * @return The output of the command.
     */
    String executeCommand(String[] args);
}
