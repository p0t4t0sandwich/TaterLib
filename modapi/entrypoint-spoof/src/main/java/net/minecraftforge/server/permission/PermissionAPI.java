/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
