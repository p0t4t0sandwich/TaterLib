/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.ModInfo;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.impl.util.VersionUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Stores information about a mod
 *
 * @param id The mod ID
 * @param name The mod name
 * @param version The mod version
 * @param platform The platform the mod is running on
 */
public record ModInfoImpl(String id, String name, String version, Platform platform)
        implements ModInfo {
    @Override
    public boolean parseRange(@NotNull String rangeString) {
        Objects.requireNonNull(rangeString, "Range string cannot be null");
        @Nullable VersionUtil.Range range = VersionUtil.Range.parse(rangeString);
        if (range == null) {
            return this.is(rangeString);
        }
        String start = range.start() == null ? "0.0.0" : range.start();
        String end = range.end() == null ? "9999.9999.9999" : range.end();
        return this.isInRange(range.startInclusive(), start, range.endInclusive(), end);
    }
}
