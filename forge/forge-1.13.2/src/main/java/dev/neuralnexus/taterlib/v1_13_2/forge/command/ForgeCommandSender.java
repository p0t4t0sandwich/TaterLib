/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13_2.forge.command;

import dev.neuralnexus.taterlib.command.CommandSender;

import net.minecraft.command.CommandSource;
import net.minecraft.util.text.TextComponentString;

import java.util.UUID;

/** The Forge implementation of {@link CommandSender} */
public class ForgeCommandSender implements CommandSender {
    private final CommandSource source;

    public ForgeCommandSender(CommandSource source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public CommandSource sender() {
        return source;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        if (source.getEntity() == null) {
            return new UUID(0, 0);
        }
        return source.getEntity().getUniqueID();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return source.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        source.sendFeedback(new TextComponentString(message), false);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return source.hasPermissionLevel(permissionLevel);
    }
}
