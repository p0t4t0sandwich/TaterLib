/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package net.minecraftforge.server.permission;

import net.minecraftforge.server.permission.nodes.PermissionNode;

import java.util.Collection;
import java.util.Collections;

public class PermissionAPI {
    private static IPermissionHandler activeHandler = null;

    public static Collection<PermissionNode<?>> getRegisteredNodes() {
        return activeHandler == null ? Collections.emptySet() : activeHandler.getRegisteredNodes();
    }

    public static IPermissionHandler getPermissionHandler() {
        return null;
    }
}
