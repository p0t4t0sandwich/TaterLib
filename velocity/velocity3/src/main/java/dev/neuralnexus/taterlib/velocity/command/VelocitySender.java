package dev.neuralnexus.taterlib.velocity.command;

import com.velocitypowered.api.command.CommandSource;

import dev.neuralnexus.taterlib.command.Sender;

import net.kyori.adventure.text.Component;

import java.util.UUID;

/** Velocity implementation of {@link Sender} */
public class VelocitySender implements Sender {
    private final CommandSource sender;

    public VelocitySender(CommandSource sender) {
        this.sender = sender;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public CommandSource getSender() {
        return sender;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return new UUID(0, 0);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "CONSOLE";
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        sender.sendMessage(Component.text(message));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
