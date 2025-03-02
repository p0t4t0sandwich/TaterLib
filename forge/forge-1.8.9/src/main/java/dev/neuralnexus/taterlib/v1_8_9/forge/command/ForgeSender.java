/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.forge.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.command.CommandSender;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.UUID;

/** Forge implementation of {@link CommandSender} */
public class ForgeSender implements CommandSender, Wrapped<ICommandSender> {
    private final ICommandSender sender;

    public ForgeSender(ICommandSender sender) {
        this.sender = sender;
    }

    @Override
    public ICommandSender unwrap() {
        return this.sender;
    }

    @Override
    public UUID uuid() {
        return TaterAPI.uuidFromName(this.sender.getName()).orElse(new UUID(0, 0));
    }

    @Override
    public String name() {
        return this.sender.getName();
    }

    @Override
    public void sendMessage(String message) {
        this.sender.addChatMessage(new ChatComponentText(message));
    }
}
