package dev.neuralnexus.taterlib.bukkit.command;

import dev.neuralnexus.taterlib.command.CommandSender;

import java.util.UUID;

/** Bukkit implementation of {@link CommandSender} */
public class BukkitCommandSender implements CommandSender {
    private final org.bukkit.command.CommandSender commandSender;

    public BukkitCommandSender(org.bukkit.command.CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public org.bukkit.command.CommandSender getSender() {
        return commandSender;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return new UUID(0, 0);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return commandSender.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        commandSender.sendMessage(message);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(String permission) {
        return commandSender.hasPermission(permission);
    }
}
