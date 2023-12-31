package dev.neuralnexus.taterlib.bungee.command;

import dev.neuralnexus.taterlib.common.command.Sender;
import net.md_5.bungee.api.CommandSender;

import java.util.UUID;

/**
 * Bungee implementation of {@link Sender}
 */
public class BungeeSender implements Sender {
    private final CommandSender sender;

    public BungeeSender(CommandSender sender) {
        this.sender = sender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID getUniqueId() {
        return new UUID(0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return sender.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMessage(String message) {
        sender.sendMessage(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
