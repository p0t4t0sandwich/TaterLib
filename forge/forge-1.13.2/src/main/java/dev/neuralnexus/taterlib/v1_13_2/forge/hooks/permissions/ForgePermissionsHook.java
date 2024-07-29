/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.hooks.permissions;

import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.v1_13_2.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.player.ForgePlayer;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.EntityPlayer;
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

        EntityPlayer player;
        if (permissible instanceof Player) {
            player = ((ForgePlayer) permissible).player();
        } else {
            CommandSource source = ((ForgeCommandSender) permissible).sender();
            if (source.getEntity() == null) {
                return false;
            }
            player = (EntityPlayer) source.getEntity();
        }
        return PermissionAPI.getPermissionHandler()
                .hasPermission(player.getGameProfile(), permission, null);
    }
}
