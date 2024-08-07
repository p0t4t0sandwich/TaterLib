/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15.forge.hooks.permissions;

import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;

import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.server.permission.PermissionAPI;

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

        net.minecraft.world.entity.player.Player player;
        if (permissible instanceof Player) {
            player = (net.minecraft.world.entity.player.Player) permissible;
        } else {
            CommandSourceStack source = (CommandSourceStack) permissible;
            if (source.getEntity() == null) {
                return false;
            }
            player = (net.minecraft.world.entity.player.Player) source.getEntity();
        }
        return PermissionAPI.getPermissionHandler()
                .hasPermission(player.getGameProfile(), permission, null);
    }
}
