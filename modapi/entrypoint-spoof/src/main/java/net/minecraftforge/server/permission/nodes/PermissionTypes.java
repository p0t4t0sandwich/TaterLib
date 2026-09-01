/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.server.permission.nodes;

public final class PermissionTypes {
    public static final PermissionType<Boolean> BOOLEAN =
            new PermissionType(Boolean.class, "boolean");
    public static final PermissionType<Integer> INTEGER =
            new PermissionType(Integer.class, "integer");
    public static final PermissionType<String> STRING = new PermissionType(String.class, "string");
}
