/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform;

import dev.neuralnexus.modapi.metadata.Platform;

public interface Hybrid {
    Platform MOHIST =
            new PlatformImpl("Mohist", "com.mohistmc.MohistMC", "com.mohistmc.MohistMCStart");
    Platform ARCLIGHT =
            new PlatformImpl(
                    "Arclight",
                    "io.izzel.arclight.api.Arclight",
                    "io.izzel.arclight.common.ArclightMain");
}
