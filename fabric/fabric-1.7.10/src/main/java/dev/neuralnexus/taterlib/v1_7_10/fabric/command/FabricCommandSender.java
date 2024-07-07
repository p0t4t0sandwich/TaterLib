/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.fabric.command;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterlib.v1_7_10.fabric.FabricTaterLibPlugin;

import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableText;

import java.util.List;
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

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public UUID uuid() {
        PlayerEntity player =
                ((List<PlayerEntity>)
                                FabricTaterLibPlugin.minecraftServer.getPlayerManager().players)
                        .stream()
                                .filter(
                                        p ->
                                                p.getName()
                                                        .asFormattedString()
                                                        .equals(
                                                                source.getName()
                                                                        .asFormattedString()))
                                .findFirst()
                                .orElse(null);

        if (player == null) {
            return new UUID(0, 0);
        }
        return player.getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return source.getName().asFormattedString();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        source.sendMessage(new TranslatableText(message));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
