/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.integrations;

import ca.stellardrift.permissionsex.PermissionsEx;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.modapi.crossperms.api.HasPermission;
import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.VelocityMeta;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.md_5.bungee.api.ProxyServer;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda"})
public class PermissionsExPermissionsProvider implements PermissionsProvider {
    private static final String PERMISSIONSEX_NAME = "PermissionsEx";
    private static final String PERMISSIONSEX_ID = "permissionsex";
    private final PermissionsEx<?> pex;

    // spotless:off
    @SuppressWarnings("UnstableApiUsage")
    public PermissionsExPermissionsProvider() {
        MetaAPI meta = MetaAPI.instance();
        PermissionsEx<?> pex = null;
        if (meta.isPlatformPresent(Platforms.BUKKIT)) {
            pex =
                    ((ca.stellardrift.permissionsex.bukkit.PermissionsExPlugin)
                                    Bukkit.getServer()
                                            .getPluginManager()
                                            .getPlugin(PERMISSIONSEX_NAME))
                            .getManager();
        } else if (meta.isPlatformPresent(Platforms.BUNGEECORD)) {
            pex =
                    ((ca.stellardrift.permissionsex.bungee.PermissionsExPlugin)
                                    ProxyServer.getInstance()
                                            .getPluginManager()
                                            .getPlugin(PERMISSIONSEX_NAME))
                            .getManager();
        } else if (meta.isPlatformPresent(Platforms.FABRIC)) {
            pex =
                    ((ca.stellardrift.permissionsex.fabric.PermissionsExMod)
                                    FabricLoader.getInstance()
                                            .getEntrypointContainers(
                                                    PERMISSIONSEX_ID, ModInitializer.class)
                                            .getFirst()
                                            .getEntrypoint())
                            .getManager();
        } else if (meta.isPlatformPresent(Platforms.SPONGE)) {
            if (meta.version().isAtLeast(MinecraftVersions.V13)) {
                pex =
                        Sponge.pluginManager()
                                .plugin(PERMISSIONSEX_ID)
                                .map(org.spongepowered.plugin.PluginContainer::instance)
                                .map(
                                        ca.stellardrift.permissionsex.sponge.PermissionsExPlugin
                                                        .class
                                                ::cast)
                                .map(
                                        ca.stellardrift.permissionsex.sponge.PermissionsExPlugin
                                                ::getManager)
                                .orElse(null);
            } else {
                pex =
                        Sponge.getPluginManager()
                                .getPlugin(PERMISSIONSEX_ID)
                                .flatMap(org.spongepowered.api.plugin.PluginContainer::getInstance)
                                .map(
                                        ca.stellardrift.permissionsex.sponge.PermissionsExPlugin
                                                        .class
                                                ::cast)
                                .map(
                                        ca.stellardrift.permissionsex.sponge.PermissionsExPlugin
                                                ::getManager)
                                .orElse(null);
            }
        } else if (meta.isPlatformPresent(Platforms.VELOCITY)) {
            pex =
                    VelocityMeta.proxyServer()
                            .getPluginManager()
                            .getPlugin(PERMISSIONSEX_ID)
                            .flatMap(com.velocitypowered.api.plugin.PluginContainer::getInstance)
                            .map(
                                    ca.stellardrift.permissionsex.velocity.PermissionsExPlugin.class
                                            ::cast)
                            .map(
                                    ca.stellardrift.permissionsex.velocity.PermissionsExPlugin
                                            ::getManager)
                            .orElse(null);
        }
        this.pex = pex;
    }

    // spotless:on

    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                Object.class,
                List.of(
                        new HasPermission<String, Object>() {
                            @Override
                            public boolean hasPermission(Object subject, String permission) {
                                return subjectHasPermission(subject, permission);
                            }
                        }));
    }

    public boolean subjectHasPermission(Object subject, String permission) {
        try {
            return pex.getSubjects(PermissionsEx.SUBJECTS_DEFAULTS)
                    .get((String) subject)
                    .get()
                    .hasPermission(permission);
        } catch (ExecutionException | InterruptedException e) {
            CrossPerms.instance()
                    .logger()
                    .error(
                            "Error checking permission via PermissionsEx for "
                                    + subject
                                    + ": "
                                    + permission,
                            e);
            return false;
        }
    }
}
