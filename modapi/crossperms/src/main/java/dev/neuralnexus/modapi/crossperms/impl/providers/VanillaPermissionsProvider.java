/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.impl.providers;

import dev.neuralnexus.modapi.crossperms.HasPermission;
import dev.neuralnexus.modapi.crossperms.PermissionsProvider;
import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.modapi.crossperms.mc.WCommandSource;
import dev.neuralnexus.modapi.crossperms.mc.WEntity;
import dev.neuralnexus.modapi.crossperms.mc.WPlayerList;
import dev.neuralnexus.modapi.crossperms.mc.WServerPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/** Vanilla permissions provider */
@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda"})
public class VanillaPermissionsProvider implements PermissionsProvider {
    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                Object.class,
                List.of(
                        new HasPermission<Integer, Object>() {
                            @Override
                            public boolean hasPermission(Object subject, Integer permission) {
                                return playerHasPermission(subject, permission);
                            }
                        }));
    }

    private boolean playerHasPermission(@NotNull Object subject, int permissionLevel) {
        // TODO: Query Bukkit vanilla objects. It's gonna suck to get all those obsfed mappings
        if (WPlayerList.is13_up) {
            if (WCommandSource.instanceOf(subject)) {
                return WCommandSource.wrap(subject).hasPermission(permissionLevel);
            } else if (WEntity.instanceOf(subject)) {
                return WServerPlayer.wrap(subject).hasPermission(permissionLevel);
            }
        }
        return PermsAPI.instance()
                .getGameProfile(subject)
                .filter(profile -> WPlayerList.hasPermissionLevel(profile, permissionLevel))
                .isPresent();
    }
}
