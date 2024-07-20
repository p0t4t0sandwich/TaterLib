/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package com.velocitypowered.api.network;

import com.velocitypowered.api.util.Ordered;

/** Fake Velocity enum. */
public enum ProtocolVersion implements Ordered<ProtocolVersion> {
    ;
    private final int protocol;
    private final String[] names;

    public static final ProtocolVersion MAXIMUM_VERSION = values()[values().length - 1];

    ProtocolVersion(int protocol, String... names) {
        this.protocol = protocol;
        this.names = names;
    }

    @Override
    public String toString() {
        return names[0];
    }
}
