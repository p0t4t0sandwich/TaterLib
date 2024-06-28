/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.bungee.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.v1_8.bungee.player.BungeePlayer;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/** Wraps a command callback into a Bungee Command. */
public class BungeeCommandWrapper extends net.md_5.bungee.api.plugin.Command {
    private final Command.Callback callback;

    public BungeeCommandWrapper(Command.Callback callback, String commandName) {
        super(commandName);
        this.callback = callback;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            callback.execute(new BungeePlayer((ProxiedPlayer) sender), this.getName(), args);
        } else {
            callback.execute(new BungeeCommandSender(sender), this.getName(), args);
        }
    }
}
