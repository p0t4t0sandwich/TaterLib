/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.PermsAPI;
import dev.neuralnexus.modapi.crossperms.api.mc.WCommandSender;
import dev.neuralnexus.modapi.crossperms.api.mc.WEntity;
import dev.neuralnexus.modapi.crossperms.api.mc.WPlayerList;
import dev.neuralnexus.modapi.crossperms.api.mc.WServerPlayer;

import org.jetbrains.annotations.NotNull;

public class VanillaPermissionsProvider implements PermissionsProvider {
    @Override
    public String id() {
        return "vanillapermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        // TODO: Query Bukkit vanilla objects. It's gonna suck to get all those obsfed mappings
        if (WPlayerList.is13_up) {
            if (WCommandSender.instanceOf(subject)) {
                return WCommandSender.wrap(subject).hasPermission(permissionLevel);
            } else if (WEntity.instanceOf(subject)) {
                return WServerPlayer.wrap(subject).hasPermission(permissionLevel);
            }
        }
        return PermsAPI.instance()
                        .getGameProfile(subject)
                        .filter(profile -> WPlayerList.hasPermissionLevel(profile, permissionLevel))
                        .isPresent();
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        return false;
    }
}
