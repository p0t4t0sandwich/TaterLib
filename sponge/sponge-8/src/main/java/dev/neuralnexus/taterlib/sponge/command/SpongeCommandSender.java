package dev.neuralnexus.taterlib.sponge.command;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.command.CommandSender;

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
    public CommandCause getSender() {
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
        // TODO: Figure out how identity works in Sponge
        //        sender.sendMessage(sender, Component.text(message));
        TaterLib.getLogger().info(message);
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
