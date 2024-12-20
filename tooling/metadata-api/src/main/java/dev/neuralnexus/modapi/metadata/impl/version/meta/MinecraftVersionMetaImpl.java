package dev.neuralnexus.modapi.metadata.impl.version.meta;

import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ProtocolType;

public class MinecraftVersionMetaImpl implements MinecraftVersion.Meta {
    private final int protocol;
    private final ProtocolType protocolType;
    private final boolean snapshot;

    public MinecraftVersionMetaImpl(int protocol, ProtocolType protocolType, boolean snapshot) {
        this.protocol = protocol;
        this.protocolType = protocolType;
        this.snapshot = snapshot;
    }

    @Override
    public int protocol() {
        return protocol;
    }

    @Override
    public ProtocolType protocolType() {
        return protocolType;
    }

    @Override
    public boolean snapshot() {
        return snapshot;
    }
}
