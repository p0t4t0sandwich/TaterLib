package dev.neuralnexus.taterlib.v1_13_2.forge.hooks.permissions;

import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.v1_13_2.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.v1_13_2.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.server.permission.PermissionAPI;

/** A hook for Forge permissions */
public class ForgePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "forgepermissions";
    }

    /** {@inheritDoc} */
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
