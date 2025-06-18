/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.impl;

import dev.neuralnexus.modapi.crossperms.HasPermission;
import dev.neuralnexus.modapi.crossperms.PermissionsProvider;
import dev.neuralnexus.modapi.crossperms.PermsAPI;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/** Permissions API implementation */
public class PermsAPIImpl implements PermsAPI {
    private static PermsAPIImpl INSTANCE;

    public static PermsAPIImpl getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new PermsAPIImpl();
        }
        return INSTANCE;
    }

    private PermsAPIImpl() {}

    private final Map<Class<?>, List<HasPermission<?, ?>>> providers = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <P, S> List<HasPermission<P, S>> providers(
            @NotNull Class<P> providerType, @NotNull Class<S> subjectType) {
        Objects.requireNonNull(providerType, "Provider type cannot be null");
        Objects.requireNonNull(subjectType, "Subject type cannot be null");

        // Get providers that extend from subjectType, and then line up with providerType
        List<Class<?>> instanceOf =
                new ArrayList<>(providers.keySet())
                        .stream().filter(subjectType::isAssignableFrom).toList();
        List<HasPermission<P, S>> list = new ArrayList<>();
        for (Class<?> clazz : instanceOf) {
            for (HasPermission<?, ?> hasPermission : providers.get(clazz)) {
                if (providerType.isAssignableFrom(hasPermission.type())) {
                    list.add((HasPermission<P, S>) hasPermission);
                }
            }
        }
        return list;
    }

    @Override
    public void registerProvider(@NotNull PermissionsProvider provider) {
        Objects.requireNonNull(provider, "Provider cannot be null");
        Map<Class<?>, List<HasPermission<?, ?>>> map = provider.getProviders();
        Objects.requireNonNull(map, "Provider returned no providers");
        map.forEach(
                (key, value) ->
                        this.providers.computeIfAbsent(key, k -> new ArrayList<>()).addAll(value));
    }
}
