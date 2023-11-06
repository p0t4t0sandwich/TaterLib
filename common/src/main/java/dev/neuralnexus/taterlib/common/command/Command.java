package dev.neuralnexus.taterlib.common.command;

public interface Command {
    @FunctionalInterface
    interface Callback {
        boolean execute(Sender sender, String label, String[] args);
    }

    /**
     * Set the name of the command.
     * @param name The name of the command.
     */
    void setName(String name);

    /**
     * Get the name of the command.
     * @return The name of the command.
     */
    String getName();

    /**
     * Get the description of the command.
     * @return The description of the command.
     */
    String getDescription();

    /**
     * Get the usage of the command.
     * @return The usage of the command.
     */
    String getUsage();

    /**
     * Get the permission of the command.
     * @return The permission of the command.
     */
    String getPermission();

    /**
     * Get the subcommand permission.
     * @param subCommand The subcommand.
     * @return The permission.
     */
    default String getPermission(String subCommand) {
        return getPermission() + "." + subCommand;
    }

    /**
     * Get the subcommand permission.
     * @param subCommands The subcommands.
     * @return The permission.
     */
    default String getPermission(String[] subCommands) {
        StringBuilder permission = new StringBuilder(getPermission());
        for (String subCommand : subCommands) {
            permission.append(".").append(subCommand);
        }
        return permission.toString();
    }

    /**
     * Execute the command.
     * @param args The arguments of the command.
     * @return The output of the command.
     */
    String execute(String[] args);

    /**
     * Execute the command.
     * @param sender The sender of the command.
     * @param label The label of the command.
     * @param args The arguments of the command.
     * @return The output of the command.
     */
    boolean execute(Sender sender, String label, String[] args);
}
