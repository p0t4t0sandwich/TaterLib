package dev.neuralnexus.taterlib.forge.hooks.permissions;

import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.commands.CommandSourceStack;
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

        net.minecraft.world.entity.player.Player player;
        if (permissible instanceof Player) {
            player = ((ForgePlayer) permissible).player();
        } else {
            CommandSourceStack source = ((ForgeCommandSender) permissible).sender();
            if (source.getEntity() == null) {
                return false;
            }
            player = (net.minecraft.world.entity.player.Player) source.getEntity();
        }
        return PermissionAPI.getPermissionHandler()
                .hasPermission(player.getGameProfile(), permission, null);
    }
}
