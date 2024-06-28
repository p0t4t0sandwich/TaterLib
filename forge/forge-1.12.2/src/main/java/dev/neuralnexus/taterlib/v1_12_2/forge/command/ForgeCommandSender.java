/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_12_2.forge.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

import java.util.UUID;

/** Bukkit implementation of {@link CommandSender} */
public class ForgeCommandSender implements CommandSender {
    private final ICommandSender sender;
    private final Command command;

    public ForgeCommandSender(ICommandSender sender, Command command) {
        this.sender = sender;
        this.command = command;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public ICommandSender sender() {
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
        sender.sendMessage(new TextComponentString(message));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return sender.canUseCommand(permissionLevel, command.name());
    }
}
