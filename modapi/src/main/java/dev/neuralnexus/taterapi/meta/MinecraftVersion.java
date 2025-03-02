/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta;

import dev.neuralnexus.taterapi.meta.impl.util.VersionUtil;
import dev.neuralnexus.taterapi.meta.impl.version.VersionComparable;
import dev.neuralnexus.taterapi.meta.impl.version.meta.MetaStore;
import dev.neuralnexus.taterapi.meta.impl.version.meta.MinecraftVersionMetaImpl;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface MinecraftVersion extends VersionComparable<MinecraftVersion> {
    /**
     * Create a MinecraftVersion from a string.
     *
     * @param version The version to create
     * @return The MinecraftVersion
     */
    static MinecraftVersion of(String version) {
        return MinecraftVersions.of(version);
    }

    /**
     * Get the "path" string version of the string representation <br>
     * Not that useful in practice, used for TaterLoader's reflection util.
     *
     * @return The path string representation of the version
     */
    @ApiStatus.Internal
    default String getPathString() {
        return "v" + this.toString().replace(".", "_");
    }

    /**
     * Get the metadata for the version.
     *
     * @return The metadata
     */
    // Not quite finished yet
    @ApiStatus.Experimental
    default Meta meta() {
        return MetaStore.getMeta(this);
    }

    @Override
    default boolean parseRange(@NotNull String rangeString) {
        Objects.requireNonNull(rangeString, "Range string cannot be null");
        @Nullable VersionUtil.Range range = VersionUtil.Range.parse(rangeString);
        if (range == null) {
            return this.is(rangeString);
        }
        MinecraftVersion start =
                range.start() == null
                        ? MinecraftVersions.UNKNOWN
                        : MinecraftVersions.of(range.start());
        MinecraftVersion end =
                range.end() == null ? MinecraftVersions.UNKNOWN : MinecraftVersions.of(range.end());
        return this.isInRange(range.startInclusive(), start, range.endInclusive(), end);
    }

    /** Represents the metadata for a Minecraft version */
    @ApiStatus.Experimental
    interface Meta {
        Meta UNKNOWN =
                new MinecraftVersionMetaImpl(new Integer[] {0x0, 0b00, 0b000, 0x0, 0x0, 0x0});

        /**
         * Get the protocol version of the Minecraft server. 0 if unknown
         *
         * @return The protocol asString
         */
        int protocol();

        /**
         * Get the protocol type of the Minecraft server. UNKNOWN if unknown
         *
         * @return The protocol type
         */
        ProtocolType protocolType();

        /**
         * Get the release type of the Minecraft server. UNKNOWN if unknown
         *
         * @return The release type
         */
        Type type();

        /**
         * Get the data version of the Minecraft server. 0 if unknown
         *
         * @return The data version
         */
        int dataVersion();

        /**
         * Get the resource pack format of the Minecraft server. 0 if unknown
         *
         * @return The resource pack format
         */
        int resourcePackFormat();

        /**
         * Get the data pack format of the Minecraft server. 0 if unknown
         *
         * @return The data version
         */
        int dataPackFormat();
    }

    /** Represents the release type */
    enum Type {
        UNKNOWN,
        SNAPSHOT,
        EXP_SNAPSHOT,
        PRE_RELEASE,
        RELEASE_CANDIDATE,
        RELEASE;

        public static Type fromInt(int i) {
            return switch (i) {
                case 1 -> SNAPSHOT;
                case 2 -> EXP_SNAPSHOT;
                case 3 -> PRE_RELEASE;
                case 4 -> RELEASE_CANDIDATE;
                case 5 -> RELEASE;
                default -> UNKNOWN;
            };
        }
    }
}
