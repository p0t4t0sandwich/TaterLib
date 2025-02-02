/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_8.bukkit.command;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.Wrapped;

import java.util.UUID;

/** Bukkit implementation of {@link CommandSender} */
public class BukkitCommandSender
        implements CommandSender, Wrapped<org.bukkit.command.CommandSender> {
    private final org.bukkit.command.CommandSender sender;

    public BukkitCommandSender(org.bukkit.command.CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public org.bukkit.command.CommandSender unwrap() {
        return this.sender;
    }

    @Override
    public UUID uuid() {
        return TaterAPIProvider.uuidFromName(this.sender.getName().asFormattedString()).orElse(new UUID(0, 0));
    }

    @Override
    public String name() {
        return this.sender.getName();
    }

    @Override
    public void sendMessage(String message) {
        this.sender.sendMessage(message);
    }
}
