/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric.command;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Permissible;

import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.minecraft.text.TranslatableText;

import java.util.UUID;

/** The Fabric implementation of {@link CommandSender} */
public class FabricCommandSender implements CommandSender {
    private final PermissibleCommandSource source;

    public FabricCommandSender(PermissibleCommandSource source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public PermissibleCommandSource sender() {
        return source;
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public UUID uuid() {
        return TaterAPIProvider.api()
                .get()
                .server()
                .getPlayer(source.getName().asFormattedString())
                .map(Permissible::uuid)
                .orElseGet(() -> new UUID(0, 0));
    }

    @Override
    public String name() {
        return source.getName().asFormattedString();
    }

    @Override
    public void sendMessage(String message) {
        source.sendMessage(new TranslatableText(message));
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    @Override
    public boolean hasPermission(String permission) {
        return TaterAPIProvider.hasPermission(this, permission);
    }
}
