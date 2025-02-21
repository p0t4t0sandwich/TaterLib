/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.version.meta;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.ProtocolType;

public final class MinecraftVersionMetaImpl implements MinecraftVersion.Meta {
    private final Integer[] data;

    public MinecraftVersionMetaImpl(Integer[] data) {
        this.data = data;
    }

    @Override
    public int protocol() {
        return data[0];
    }

    @Override
    public ProtocolType protocolType() {
        return ProtocolType.fromInt(data[1]);
    }

    @Override
    public MinecraftVersion.Type type() {
        return MinecraftVersion.Type.fromInt(data[2]);
    }

    @Override
    public int dataVersion() {
        return data[3];
    }

    @Override
    public int resourcePackFormat() {
        return data[4];
    }

    @Override
    public int dataPackFormat() {
        return data[5];
    }
}
