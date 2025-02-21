/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.Platform;

public interface Sponge {
    Platform SPONGE = new PlatformImpl("Sponge", "org.spongepowered.api.Sponge");

    // TODO: Decide if these should be added
    // Platform SPONGEVANILLA = new PlatformImpl("SpongeVanilla");
    // Platform SPONGEFORGE = new PlatformImpl("SpongeForge");
    // Platform LOOFAH = new PlatformImpl("Loofah");
}
