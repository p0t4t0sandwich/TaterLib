/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19.forge.hooks.permissions;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterlib.v1_19.vanilla.command.VanillaCommandSender;
import dev.neuralnexus.taterlib.v1_19.vanilla.entity.player.VanillaPlayer;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.permission.PermissionAPI;
import net.minecraftforge.server.permission.nodes.PermissionTypes;

/** A hook for Forge permissions */
public class ForgePermissionsHook implements PermissionsHook {
    @Override
    public String name() {
        return "forgepermissions";
    }

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
