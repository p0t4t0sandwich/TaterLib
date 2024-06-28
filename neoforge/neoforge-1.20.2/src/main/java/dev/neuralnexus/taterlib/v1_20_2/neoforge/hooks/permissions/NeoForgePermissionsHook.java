/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20_2.neoforge.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_20.vanilla.command.VanillaCommandSender;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.permission.PermissionAPI;
import net.neoforged.neoforge.server.permission.nodes.PermissionTypes;

/** A hook for NeoForge permissions */
public class NeoForgePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "neoforgepermissions";
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible.hasPermission(4)) {
            return true;
        }

        ServerPlayer player;
        if (permissible instanceof Player) {
            player = (ServerPlayer) ((VanillaPlayer) permissible).player();
        } else if (permissible instanceof CommandSender) {
            player = (ServerPlayer) ((VanillaCommandSender) permissible).sender().getEntity();
        } else {
            player = null;
        }

        if (player != null) {
            return PermissionAPI.getRegisteredNodes().stream()
                    .filter(node -> node.getType() == PermissionTypes.BOOLEAN)
                    .filter(node -> node.getNodeName().equals(permission))
                    .anyMatch(
                            node ->
                                    (boolean)
                                            node.getDefaultResolver()
                                                    .resolve(player, permissible.uuid()));
        }
        return false;
    }
}
