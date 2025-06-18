/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.impl.providers;

import dev.neuralnexus.modapi.crossperms.HasPermission;
import dev.neuralnexus.modapi.crossperms.PermissionsProvider;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/** Bukkit permissions provider */
@SuppressWarnings("Convert2Lambda")
public class BukkitPermissionsProvider implements PermissionsProvider {
    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                CommandSender.class,
                List.of(
                        new HasPermission<Integer, CommandSender>() {
                            @Override
                            public boolean hasPermission(
                                    CommandSender subject, Integer permission) {
                                return subject instanceof CommandSender sender && sender.isOp();
                            }
                        },
                        new HasPermission<String, CommandSender>() {
                            @Override
                            public boolean hasPermission(CommandSender subject, String permission) {
                                return subject instanceof CommandSender sender
                                        && sender.hasPermission(permission);
                            }
                        }));
    }
}
