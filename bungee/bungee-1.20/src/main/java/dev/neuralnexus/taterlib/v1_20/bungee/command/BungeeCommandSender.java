/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20.bungee.command;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.Wrapped;

import java.util.UUID;

/** Bungee implementation of {@link CommandSender} */
public class BungeeCommandSender
        implements CommandSender, Wrapped<net.md_5.bungee.api.CommandSender> {
    private final net.md_5.bungee.api.CommandSender sender;

    public BungeeCommandSender(net.md_5.bungee.api.CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public net.md_5.bungee.api.CommandSender unwrap() {
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

    @SuppressWarnings("deprecation")
    @Override
    public void sendMessage(String message) {
        this.sender.sendMessage(message);
    }
}
