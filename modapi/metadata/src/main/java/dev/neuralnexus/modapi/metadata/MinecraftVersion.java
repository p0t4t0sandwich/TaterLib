/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import static dev.neuralnexus.modapi.metadata.impl.version.FlexVerComparator.compare;

import dev.neuralnexus.modapi.metadata.impl.version.meta.MetaStore;
import dev.neuralnexus.modapi.metadata.impl.version.meta.MinecraftVersionMetaImpl;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public interface MinecraftVersion {
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

    /**
     * Get if The version of Minecraft the server is running is equal to the specified version.
     *
     * @param version The version to check
     * @return If The version of Minecraft the server is running is equal to the specified version
     */
    default boolean is(@NotNull String version) throws NullPointerException {
        Objects.requireNonNull(version, "Version cannot be null");
        return compare(this.toString(), version) == 0;
    }

    /**
     * Get if The version of Minecraft the server is running is equal to the specified version.
     *
     * @param version The version to check
     * @return If The version of Minecraft the server is running is equal to the specified version
     */
    default boolean is(@NotNull MinecraftVersion version) throws NullPointerException {
        Objects.requireNonNull(version, "Version cannot be null");
        return this.is(version.toString());
    }

    /**
     * Get if The version of Minecraft the server is running is within the defined range.
     *
     * @param startInclusive The start of the range
     * @param start The start of the range
     * @param endInclusive The end of the range
     * @param end The end of the range
     * @return If The version of Minecraft the server is running is within the defined range
     * @throws NullPointerException If the start or end version is null
     */
    default boolean isInRange(
            boolean startInclusive,
            @NotNull MinecraftVersion start,
            boolean endInclusive,
            @NotNull MinecraftVersion end)
            throws NullPointerException {
        Objects.requireNonNull(start, "Start version cannot be null");
        Objects.requireNonNull(end, "End version cannot be null");
        MinecraftVersion[] VERSIONS = MinecraftVersions.Cache.versions();
        if (start == MinecraftVersions.UNKNOWN) {
            start = VERSIONS[0];
        }
        if (end == MinecraftVersions.UNKNOWN) {
            end = VERSIONS[VERSIONS.length - 1];
        }
        int compStart = compare(this.toString(), start.toString());
        int compEnd = compare(this.toString(), end.toString());
        return (startInclusive ? compStart >= 0 : compStart > 0)
                && (endInclusive ? compEnd <= 0 : compEnd < 0);
    }

    /**
     * Get if The version of Minecraft the server is running is within the defined range. Assumed to
     * be an inclusive range.
     *
     * @param start The start of the range
     * @param end The end of the range
     * @return If The version of Minecraft the server is running is within the defined range
     * @throws NullPointerException If the start or end version is null
     */
    default boolean isInRange(@NotNull MinecraftVersion start, @NotNull MinecraftVersion end)
            throws NullPointerException {
        return this.isInRange(true, start, true, end);
    }

    /**
     * Get if The version of Minecraft the server is running is within the defined range. <br>
     * Strings are read in the format of: <b>(1.17,1.20]</b> or <b>[1.17,)</b> or <b>(,1.20]</b>
     *
     * @param rangeString The range to check
     * @return If The version of Minecraft the server is running is within the defined range
     * @throws NullPointerException If the range string is null
     */
    default boolean parseRange(@NotNull String rangeString) throws NullPointerException {
        Objects.requireNonNull(rangeString, "Range string cannot be null");
        rangeString = rangeString.trim();
        boolean startInclusive;
        boolean endInclusive;
        if (rangeString.charAt(0) == '(') {
            startInclusive = false;
        } else if (rangeString.charAt(0) == '[') {
            startInclusive = true;
        } else {
            return this.is(rangeString);
        }
        if (rangeString.charAt(rangeString.length() - 1) == ')') {
            endInclusive = false;
        } else if (rangeString.charAt(rangeString.length() - 1) == ']') {
            endInclusive = true;
        } else {
            return this.is(rangeString);
        }
        rangeString = rangeString.substring(1, rangeString.length() - 1);
        MinecraftVersion start;
        MinecraftVersion end;
        if (rangeString.startsWith(",")) {
            start = MinecraftVersions.UNKNOWN;
            end = MinecraftVersions.of(rangeString.substring(1));
        } else if (rangeString.endsWith(",")) {
            start = MinecraftVersions.of(rangeString.substring(0, rangeString.length() - 1));
            end = MinecraftVersions.UNKNOWN;
        } else {
            String[] range = rangeString.split(",");
            start = MinecraftVersions.of(range[0]);
            end = MinecraftVersions.of(range[1]);
        }
        return this.isInRange(startInclusive, start, endInclusive, end);
    }

    /**
     * Get if The version of Minecraft the server is running is older than the specified version.
     *
     * @param version The version to check
     * @return If the Minecraft version is older
     * @throws NullPointerException If the version is null
     */
    default boolean isNewerThan(@NotNull String version) throws NullPointerException {
        Objects.requireNonNull(version, "Version cannot be null");
        return compare(this.toString(), version) > 0;
    }

    /**
     * Get if The version of Minecraft the server is running is older than the specified version.
     *
     * @param version The version to check
     * @return If the Minecraft version is older
     * @throws NullPointerException If the version is null
     */
    default boolean isNewerThan(@NotNull MinecraftVersion version) throws NullPointerException {
        Objects.requireNonNull(version, "Version cannot be null");
        return this.isNewerThan(version.toString());
    }

    /**
     * Get if The version of Minecraft the server is running is equal to or newer than the specified
     * version.
     *
     * @param version The version to check
     * @return If the Minecraft version is equal to or newer
     * @throws NullPointerException If the version is null
     */
    default boolean isAtLeast(@NotNull String version) throws NullPointerException {
        Objects.requireNonNull(version, "Version cannot be null");
        return compare(this.toString(), version) >= 0;
    }

    /**
     * Get if The version of Minecraft the server is running is equal to or newer than the specified
     * version.
     *
     * @param version The version to check
     * @return If the Minecraft version is equal to or newer
     * @throws NullPointerException If the version is null
     */
    default boolean isAtLeast(@NotNull MinecraftVersion version) throws NullPointerException {
        Objects.requireNonNull(version, "Version cannot be null");
        return this.isAtLeast(version.toString());
    }

    /**
     * Get if The version of Minecraft the server is running is older than the specified version.
     *
     * @param version The version to check
     * @return If the Minecraft version is newer
     * @throws NullPointerException If the version is null
     */
    default boolean isOlderThan(@NotNull String version) throws NullPointerException {
        Objects.requireNonNull(version, "Version cannot be null");
        return compare(this.toString(), version) < 0;
    }

    /**
     * Get if The version of Minecraft the server is running is older than the specified version.
     *
     * @param version The version to check
     * @return If the Minecraft version is newer
     * @throws NullPointerException If the version is null
     */
    default boolean isOlderThan(@NotNull MinecraftVersion version) throws NullPointerException {
        Objects.requireNonNull(version, "Version cannot be null");
        return this.isOlderThan(version.toString());
    }

    /**
     * Get if The version of Minecraft the server is running is equal to or older than the specified
     * version.
     *
     * @param version The version to check
     * @return If the Minecraft version is equal to or older
     * @throws NullPointerException If the version is null
     */
    default boolean isAtMost(@NotNull String version) throws NullPointerException {
        Objects.requireNonNull(version, "Version cannot be null");
        return compare(this.toString(), version) <= 0;
    }

    /**
     * Get if The version of Minecraft the server is running is equal to or older than the specified
     * version.
     *
     * @param version The version to check
     * @return If the Minecraft version is equal to or older
     * @throws NullPointerException If the version is null
     */
    default boolean isAtMost(@NotNull MinecraftVersion version) throws NullPointerException {
        Objects.requireNonNull(version, "Version cannot be null");
        return this.isAtMost(version.toString());
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
