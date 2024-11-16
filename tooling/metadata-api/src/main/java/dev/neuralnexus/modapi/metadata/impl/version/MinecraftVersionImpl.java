/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.version;

import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ProtocolType;

public class MinecraftVersionImpl implements MinecraftVersion {
    private final String version;
    private int protocol = MinecraftVersion.UNKNOWN_PROTOCOL;
    private ProtocolType protocolType = ProtocolType.UNKNOWN;
    private boolean snapshot = false;

    private MinecraftVersionImpl(String version) {
        this.version = version;
    }

    private MinecraftVersionImpl(String version, int protocol) {
        this.version = version;
        this.protocol = protocol;
    }

    private MinecraftVersionImpl(String version, int protocol, boolean snapshot) {
        this.version = version;
        this.protocol = protocol;
        this.snapshot = snapshot;
    }

    private MinecraftVersionImpl(String version, int protocol, ProtocolType protocolType) {
        this.version = version;
        this.protocol = protocol;
        this.protocolType = protocolType;
    }

    private MinecraftVersionImpl(
            String version, int protocol, ProtocolType protocolType, boolean snapshot) {
        this.version = version;
        this.protocol = protocol;
        this.protocolType = protocolType;
        this.snapshot = snapshot;
    }

    public static MinecraftVersionImpl of(String version) {
        return new MinecraftVersionImpl(version);
    }

    public static MinecraftVersionImpl of(String version, int protocol, ProtocolType protocolType) {
        return new MinecraftVersionImpl(version, protocol, protocolType);
    }

    public static MinecraftVersionImpl of(
            String version, int protocol, ProtocolType protocolType, boolean snapshot) {
        return new MinecraftVersionImpl(version, protocol, protocolType, snapshot);
    }

    /** Purely for archival purposes, for protocol versions before the version was reset in Alpha */
    public static MinecraftVersionImpl ignoreProto(
            String version, int protocol, boolean disregardProtocol) {
        if (disregardProtocol) {
            protocol = MinecraftVersion.UNKNOWN_PROTOCOL;
        }
        return new MinecraftVersionImpl(version, protocol);
    }

    @Override
    public String version() {
        return version;
    }

    @Override
    public int protocol() {
        return protocol;
    }

    @Override
    public boolean snapshot() {
        return snapshot;
    }

    /**
     * Get the version of Minecraft the server is running.
     *
     * @return The version of Minecraft the server is running
     */
    @Override
    public String toString() {
        return this.version;
    }
}
