package dev.neuralnexus.taterlib.v1_19.sponge.command;

import dev.neuralnexus.taterlib.command.CommandSender;

import net.kyori.adventure.text.Component;

import org.spongepowered.api.command.CommandCause;

import java.util.UUID;

/** Sponge implementation of {@link CommandSender} */
public class SpongeCommandSender implements CommandSender {
    private final CommandCause sender;

    public SpongeCommandSender(CommandCause sender) {
        this.sender = sender;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public CommandCause sender() {
        return sender;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return new UUID(0, 0);
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
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
