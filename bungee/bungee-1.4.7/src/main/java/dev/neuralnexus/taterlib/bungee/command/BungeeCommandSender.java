package dev.neuralnexus.taterlib.bungee.command;

import dev.neuralnexus.taterlib.command.CommandSender;

import java.util.UUID;

/** Bungee implementation of {@link CommandSender} */
public class BungeeCommandSender implements CommandSender {
    private final net.md_5.bungee.api.CommandSender commandSender;

    public BungeeCommandSender(net.md_5.bungee.api.CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public net.md_5.bungee.api.CommandSender getSender() {
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
