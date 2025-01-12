/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
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
