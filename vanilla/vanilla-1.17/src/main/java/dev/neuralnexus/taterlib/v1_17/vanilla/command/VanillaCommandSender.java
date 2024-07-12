/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_17.vanilla.command;

import dev.neuralnexus.taterapi.command.CommandSender;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.util.UUID;

/** The Vanilla implementation of {@link CommandSender} */
public class VanillaCommandSender implements CommandSender {
    private final CommandSourceStack source;

    public VanillaCommandSender(CommandSourceStack source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public CommandSourceStack sender() {
        return source;
    }

    @Override
    public UUID uuid() {
        if (source.getEntity() == null) {
            return new UUID(0, 0);
        }
        return source.getEntity().getUUID();
    }

    @Override
    public String name() {
        return source.getTextName();
    }

    @Override
    public void sendMessage(String message) {
        source.sendSuccess(Component.nullToEmpty(message), false);
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return source.hasPermission(permissionLevel);
    }
}
