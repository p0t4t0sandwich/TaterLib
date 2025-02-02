/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.fabric.command;

import dev.neuralnexus.taterapi.TaterAPIProvider;
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
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public UUID uuid() {
        if (this.sender.getEntity() == null) {
            return TaterAPIProvider.api()
                    .get()
                    .server()
                    .getPlayer(this.sender.getName().asFormattedString())
                    .map(Identifiable::uuid)
                    .orElseGet(() -> new UUID(0, 0));
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
