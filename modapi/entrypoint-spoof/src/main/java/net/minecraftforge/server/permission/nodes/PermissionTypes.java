/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package net.minecraftforge.server.permission.nodes;

public final class PermissionTypes {
    public static final PermissionType<Boolean> BOOLEAN =
            new PermissionType(Boolean.class, "boolean");
    public static final PermissionType<Integer> INTEGER =
            new PermissionType(Integer.class, "integer");
    public static final PermissionType<String> STRING = new PermissionType(String.class, "string");
}
