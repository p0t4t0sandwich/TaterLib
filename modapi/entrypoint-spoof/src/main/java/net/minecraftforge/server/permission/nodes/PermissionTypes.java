package net.minecraftforge.server.permission.nodes;

public final class PermissionTypes {
    public static final PermissionType<Boolean> BOOLEAN = new PermissionType(Boolean.class, "boolean");
    public static final PermissionType<Integer> INTEGER = new PermissionType(Integer.class, "integer");
    public static final PermissionType<String> STRING = new PermissionType(String.class, "string");
}
