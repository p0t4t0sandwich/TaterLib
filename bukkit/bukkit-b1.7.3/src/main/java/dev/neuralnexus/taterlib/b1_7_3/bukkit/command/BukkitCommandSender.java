/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.b1_7_3.bukkit.command;

import dev.neuralnexus.taterapi.command.CommandSender;

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
    public org.bukkit.command.CommandSender sender() {
        return commandSender;
    }

    @Override
    public UUID uuid() {
        return new UUID(0, 0);
    }

    @Override
    public String name() {
        return commandSender.getName();
    }

    @Override
    public void sendMessage(String message) {
        commandSender.sendMessage(message);
    }
}
