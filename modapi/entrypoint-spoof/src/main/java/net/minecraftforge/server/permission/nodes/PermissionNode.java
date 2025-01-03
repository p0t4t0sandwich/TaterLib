package net.minecraftforge.server.permission.nodes;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public final class PermissionNode<T> {
    public String getNodeName() {
        return null;
    }

    public PermissionType<T> getType() {
        return null;
    }

    public PermissionResolver<T> getDefaultResolver() {
        return null;
    }

    @FunctionalInterface
    public interface PermissionResolver<T> {
//        T resolve(@Nullable net.minecraft.src.C_13 serverPlayer, UUID uUID, PermissionDynamicContext<?>... permissionDynamicContexts);
        T resolve(@Nullable Object serverPlayer, UUID uUID, PermissionDynamicContext<?>... permissionDynamicContexts);
    }
}
