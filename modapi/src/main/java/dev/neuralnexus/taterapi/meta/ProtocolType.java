/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta;

public enum ProtocolType {
    UNKNOWN,
    OLD_PRE_NETTY,
    PRE_NETTY,
    NETTY;

    public static ProtocolType fromInt(int i) {
        return switch (i) {
            case 1 -> OLD_PRE_NETTY;
            case 2 -> PRE_NETTY;
            case 3 -> NETTY;
            default -> UNKNOWN;
        };
    }
}
