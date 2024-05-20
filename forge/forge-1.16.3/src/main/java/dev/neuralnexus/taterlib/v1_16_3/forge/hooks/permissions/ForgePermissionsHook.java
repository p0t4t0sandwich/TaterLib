package dev.neuralnexus.taterlib.v1_16_3.forge.hooks.permissions;

import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.v1_16_3.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.v1_16_3.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
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

        PlayerEntity player;
        if (permissible instanceof Player) {
            player = ((ForgePlayer) permissible).player();
        } else {
            CommandSource source = ((ForgeCommandSender) permissible).sender();
            if (source.getEntity() == null) {
                return false;
            }
            player = (PlayerEntity) source.getEntity();
        }
        return PermissionAPI.getPermissionHandler()
                .hasPermission(player.getGameProfile(), permission, null);
    }
}
