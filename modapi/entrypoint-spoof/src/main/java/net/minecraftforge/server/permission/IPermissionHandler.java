package net.minecraftforge.server.permission;

import com.mojang.authlib.GameProfile;

import net.minecraftforge.server.permission.context.IContext;

import net.minecraftforge.server.permission.nodes.PermissionNode;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface IPermissionHandler {
    boolean hasPermission(GameProfile gameProfile, String string, @Nullable IContext iContext);

    Collection<PermissionNode<?>> getRegisteredNodes();
}
