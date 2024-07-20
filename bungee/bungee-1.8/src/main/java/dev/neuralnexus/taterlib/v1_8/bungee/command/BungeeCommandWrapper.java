/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8.bungee.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterlib.v1_8.bungee.entity.player.BungeePlayer;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/** Wraps a command callback into a Bungee Command. */
public class BungeeCommandWrapper extends net.md_5.bungee.api.plugin.Command {
    private final Command command;

    public BungeeCommandWrapper(Command command) {
        super(command.name());
        this.command = command;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            command.execute(new BungeePlayer((ProxiedPlayer) sender), this.getName(), args);
        } else {
            command.execute(new BungeeCommandSender(sender), this.getName(), args);
        }
    }
}
