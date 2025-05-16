/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.command;

import dev.neuralnexus.taterapi.command.Command;

import net.md_5.bungee.api.CommandSender;

/** Wraps a command callback into a Bungee Command. */
public class BungeeCommandWrapper extends net.md_5.bungee.api.plugin.Command {
    private final Command command;

    public BungeeCommandWrapper(Command command) {
        super(command.name());
        this.command = command;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        command.execute(new BungeeCommandSender(sender), this.getName(), args);
    }
}
