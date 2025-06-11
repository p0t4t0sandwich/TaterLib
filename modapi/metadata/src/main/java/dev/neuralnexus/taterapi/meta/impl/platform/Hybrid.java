/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform;

import dev.neuralnexus.taterapi.meta.Platform;

public interface Hybrid {
    Platform ARCLIGHT =
            new PlatformImpl(
                    "Arclight",
                    "io.izzel.arclight.api.Arclight",
                    "io.izzel.arclight.common.ArclightMain");
}
