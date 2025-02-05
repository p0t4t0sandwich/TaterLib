package dev.neuralnexus.modapi.crossperms.api.impl.integrations;

import dev.neuralnexus.modapi.crossperms.api.HasPermission;
import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

import net.luckperms.api.model.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LuckPermsPermissionsProvider implements PermissionsProvider {
    private final LuckPerms luckPerms;

    public LuckPermsPermissionsProvider() {
        this.luckPerms = LuckPermsProvider.get();
    }

    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                UUID.class,
                List.of(
                        new HasPermission<String, UUID>() {
                            @Override
                            public boolean hasPermission(
                                    UUID subject, String permission) {
                                User user = luckPerms.getUserManager().getUser(subject);
                                return user != null
                                        && user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
                            }
                        },
                        new HasPermission<String, String>() {
                            @Override
                            public boolean hasPermission(String subject, String permission) {
                                User user = luckPerms.getUserManager().getUser(subject);
                                return user != null
                                        && user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
                            }
                        }));
    }
}
