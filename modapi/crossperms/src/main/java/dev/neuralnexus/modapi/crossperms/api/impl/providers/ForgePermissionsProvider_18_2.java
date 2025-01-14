/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;

import net.minecraftforge.server.permission.PermissionAPI;
import net.minecraftforge.server.permission.nodes.PermissionTypes;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;

/** Forge permissions provider */
public class ForgePermissionsProvider_18_2 implements PermissionsProvider {
    private static final Logger logger = Logger.create("ForgePermissionsProvider_18_2");

    @Override
    public String id() {
        return "forgepermissions";
    }

    private static final Class<?> COMMAND_SOURCE_STACK;

    static {
        Class<?> clazz = null;
        try {
            if (MetaAPI.instance().version().isAtLeast(MinecraftVersions.V20_5)) {
                clazz = Class.forName("net.minecraft.commands.CommandSourceStack");
            } else {
                clazz = Class.forName("net.minecraft.src.C_2969_");
            }
        } catch (ClassNotFoundException e) {
            logger.error("Failed to find CommandSourceStack class", e);
        }
        COMMAND_SOURCE_STACK = clazz;
    }

    private static final Method CSS_GET_ENTITY;

    static {
        Method method = null;
        try {
            if (MetaAPI.instance().version().isAtLeast(MinecraftVersions.V20_5)) {
                method = COMMAND_SOURCE_STACK.getDeclaredMethod("getEntity");
            } else {
                method = COMMAND_SOURCE_STACK.getDeclaredMethod("m_81373_");
            }
        } catch (NoSuchMethodException e) {
            logger.error("Failed to find getEntity method in CommandSourceStack class", e);
        }
        CSS_GET_ENTITY = method;
    }

    private static final Class<?> SERVER_PLAYER;

    static {
        Class<?> clazz = null;
        try {
            if (MetaAPI.instance().version().isAtLeast(MinecraftVersions.V20_5)) {
                clazz = Class.forName("net.minecraft.server.level.ServerPlayer");
            } else {
                clazz = Class.forName("net.minecraft.src.C_13");
            }
        } catch (ClassNotFoundException e) {
            logger.error("Failed to find ServerPlayer class", e);
        }
        SERVER_PLAYER = clazz;
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        return this.hasPermission(subject, permissionLevel, null);
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        Objects.requireNonNull(permission, "Permission cannot be null");
        boolean result;
        Object player = null;
        UUID uuid = null;
        if (COMMAND_SOURCE_STACK.isInstance(subject)) {
            try {
                Object entity = CSS_GET_ENTITY.invoke(subject);
                if (SERVER_PLAYER.isInstance(entity)) {
                    player = entity;
                }
            } catch (Exception e) {
                logger.error("Failed to invoke getEntity method in CommandSourceStack class", e);
            }
        } else if (SERVER_PLAYER.isInstance(subject)) {
            player = subject;
        }
        if (null != player) {
            try {
                //                uuid = (UUID) SP_GET_UUID.invoke(player);
            } catch (Exception e) {
                logger.error("Failed to invoke getUUID method in ServerPlayer class", e);
            }
        }
        if (null == player || null == uuid) {
            logger.error("Failed to get player object or UUID for " + subject);
            return false;
        }

        Object finalPlayer = player;
        UUID finalUuid = uuid;
        result =
                PermissionAPI.getRegisteredNodes().stream()
                        .filter(node -> node.getType() == PermissionTypes.BOOLEAN)
                        .filter(node -> node.getNodeName().equals(permission))
                        .anyMatch(
                                node ->
                                        (boolean)
                                                node.getDefaultResolver()
                                                        .resolve(finalPlayer, finalUuid));
        return result | this.hasPermission(subject, 4);
    }
}
