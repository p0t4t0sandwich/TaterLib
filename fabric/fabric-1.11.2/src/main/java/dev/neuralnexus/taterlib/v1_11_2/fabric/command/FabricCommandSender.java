/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_11_2.fabric.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Identifiable;

import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.minecraft.text.TranslatableText;

import java.util.UUID;

/** The Fabric implementation of {@link CommandSender} */
public class FabricCommandSender implements CommandSender, Wrapped<PermissibleCommandSource> {
    private final PermissibleCommandSource sender;

    public FabricCommandSender(PermissibleCommandSource sender) {
        this.sender = sender;
    }

    @Override
    public PermissibleCommandSource unwrap() {
        return this.sender;
    }

    @Override
    public UUID uuid() {
        if (this.sender.getEntity() == null) {
            return TaterAPI.uuidFromName(this.sender.getName().asFormattedString()).orElse(new UUID(0, 0));
        }
        return this.sender.getEntity().getUuid();
    }

    @Override
    public String name() {
        return this.sender.getName().asFormattedString();
    }

    @Override
    public void sendMessage(String message) {
        this.sender.sendMessage(new TranslatableText(message));
    }
}
