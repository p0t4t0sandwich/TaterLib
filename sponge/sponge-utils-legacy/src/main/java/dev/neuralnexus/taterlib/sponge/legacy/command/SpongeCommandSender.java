/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.sponge.legacy.command;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.command.CommandSender;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import java.util.UUID;

/** Sponge implementation of {@link CommandSender} */
public class SpongeCommandSender implements CommandSender, Wrapped<CommandSource> {
    private final CommandSource sender;

    public SpongeCommandSender(CommandSource sender) {
        this.sender = sender;
    }

    @Override
    public CommandSource unwrap() {
        return this.sender;
    }

    @Override
    public UUID uuid() {
        return TaterAPIProvider.uuidFromName(this.sender.getName()).orElse(new UUID(0, 0));
    }

    @Override
    public String name() {
        return this.sender.getName();
    }

    @Override
    public void sendMessage(String message) {
        this.sender.sendMessage(Text.of(message));
    }
}
