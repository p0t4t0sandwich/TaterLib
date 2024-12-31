package net.minecraftforge.server.permission;

import com.mojang.authlib.GameProfile;

import net.minecraftforge.server.permission.context.IContext;

import org.jetbrains.annotations.Nullable;

public interface IPermissionHandler {
    boolean hasPermission(GameProfile gameProfile, String string, @Nullable IContext iContext);
}
