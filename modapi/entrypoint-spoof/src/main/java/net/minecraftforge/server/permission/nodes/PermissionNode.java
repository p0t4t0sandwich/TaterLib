/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package net.minecraftforge.server.permission.nodes;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public final class PermissionNode<T> {
    public String getNodeName() {
        return "";
    }

    public PermissionType<T> getType() {
        return null;
    }

    public PermissionResolver<T> getDefaultResolver() {
        return (PermissionResolver) new Object();
    }

    @FunctionalInterface
    public interface PermissionResolver<T> {
        //        T resolve(@Nullable net.minecraft.src.C_13 serverPlayer, UUID uUID,
        // PermissionDynamicContext<?>... permissionDynamicContexts);
        T resolve(
                @Nullable Object serverPlayer,
                UUID uUID,
                PermissionDynamicContext<?>... permissionDynamicContexts);
    }
}
