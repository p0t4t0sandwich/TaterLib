/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.command.CommandSender;

import net.minecraft.command.CommandSource;
import net.minecraft.util.text.TextComponentString;

import java.util.UUID;

/** Forge implementation of {@link CommandSender} */
public class ForgeSender implements CommandSender, Wrapped<CommandSource> {
    private final CommandSource sender;

    public ForgeSender(CommandSource sender) {
        this.sender = sender;
    }

    @Override
    public CommandSource unwrap() {
        return this.sender;
    }

    @Override
    public UUID uuid() {
        return TaterAPI.uuidFromName(this.sender.getName()).orElse(TaterAPI.NIL_UUID);
    }

    @Override
    public String name() {
        return this.sender.getName();
    }

    @Override
    public void sendMessage(String message) {
        this.sender.sendFeedback(new TextComponentString(message), false);
    }
}
