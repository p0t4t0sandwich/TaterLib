/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/** Bukkit permissions provider */
public class BukkitPermissionsProvider implements PermissionsProvider {
    @Override
    public String id() {
        return "bukkitpermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        return subject instanceof CommandSender sender && sender.isOp();
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        return subject instanceof CommandSender sender && sender.hasPermission(permission);
    }
}
