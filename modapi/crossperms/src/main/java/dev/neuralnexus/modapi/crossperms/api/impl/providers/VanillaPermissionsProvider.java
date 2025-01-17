/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import dev.neuralnexus.modapi.crossperms.api.HasPermission;
import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.PermsAPI;
import dev.neuralnexus.modapi.crossperms.api.mc.WCommandSender;
import dev.neuralnexus.modapi.crossperms.api.mc.WEntity;
import dev.neuralnexus.modapi.crossperms.api.mc.WPlayerList;
import dev.neuralnexus.modapi.crossperms.api.mc.WServerPlayer;

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
}
