/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_2_5.bukkit.command;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Identifiable;
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
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public UUID uuid() {
        return TaterAPIProvider.api()
                .get()
                .server()
                .getPlayer(this.sender.getName())
                .map(Identifiable::uuid)
                .orElseGet(() -> new UUID(0, 0));
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
