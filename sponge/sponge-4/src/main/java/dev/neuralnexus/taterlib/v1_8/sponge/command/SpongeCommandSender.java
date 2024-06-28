/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.command;

import dev.neuralnexus.taterlib.command.CommandSender;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import java.util.UUID;

/** Sponge implementation of {@link CommandSender} */
public class SpongeCommandSender implements CommandSender {
    private final CommandSource sender;

    public SpongeCommandSender(CommandSource sender) {
        this.sender = sender;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public CommandSource sender() {
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
        return sender.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        sender.sendMessage(Text.of(message));
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
