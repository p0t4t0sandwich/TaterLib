package dev.neuralnexus.taterlib.forge.hooks.permissions;

import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.command.ICommandSender;
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
            ICommandSender source = ((ForgeCommandSender) permissible).sender();
            if (source.getCommandSenderEntity() == null) {
                return false;
            }
            player = (EntityPlayer) source.getCommandSenderEntity();
        }
        return PermissionAPI.getPermissionHandler()
                .hasPermission(player.getGameProfile(), permission, null);
    }
}
