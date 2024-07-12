/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19.sponge.command;

import dev.neuralnexus.taterapi.command.CommandSender;

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

    @Override
    public UUID uuid() {
        return new UUID(0, 0);
    }

    @Override
    public String name() {
        return "CONSOLE";
    }

    @Override
    public void sendMessage(String message) {
        sender.sendMessage(Component.text(message));
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
