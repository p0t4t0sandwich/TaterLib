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
