package dev.neuralnexus.taterlib.bukkit.command;

import dev.neuralnexus.taterlib.common.command.Sender;
import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * Bukkit implementation of {@link Sender}
 */
public class BukkitSender implements Sender {
    private final CommandSender sender;

    public BukkitSender(CommandSender sender) {
        this.sender = sender;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUUID() {
        return new UUID(0, 0);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return sender.getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendMessage(String message) {
        sender.sendMessage(message);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
