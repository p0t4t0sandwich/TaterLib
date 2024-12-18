/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import dev.neuralnexus.modapi.metadata.impl.version.MinecraftVersionImpl;

import java.util.HashMap;
import java.util.Map;

public interface MinecraftVersion {
    String Unknown = "unknown";
    int UNKNOWN_PROTOCOL = -1;
    MinecraftVersion UNKNOWN =
            MinecraftVersionImpl.of(Unknown, UNKNOWN_PROTOCOL, ProtocolType.UNKNOWN);
    Map<String, Integer> OrdinalCache = new HashMap<>();

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
     * Get the version of Minecraft the server is running.
     *
     * @return The version
     */
    String version();

    /**
     * Get the protocol version of the Minecraft server. -1 if unknown
     *
     * @return The protocol version
     */
    int protocol();

    /**
     * Get if the version is a snapshot.
     *
     * @return If the version is a snapshot
     */
    boolean snapshot();

    /**
     * Get the ordinal of the version in the list of versions. (Cursed alternative to enums)
     *
     * @return The ordinal
     */
    default int ord() {
        String ver = this.version();
        if (OrdinalCache.containsKey(ver)) {
            return OrdinalCache.get(ver);
        }
        int ord = -1;
        for (MinecraftVersion v : MinecraftVersions.Cache.versions()) {
            ord++;
            if (v.version().equals(ver)) {
                return ord;
            }
        }
        OrdinalCache.put(ver, ord);
        return ord;
    }

    /**
     * Get if the version of Minecraft the server is running is equal to the specified version.
     *
     * @param version The version to check
     * @return If the version of Minecraft the server is running is equal to the specified version
     */
    default boolean is(String version) {
        return this.version().equals(version);
    }

    /**
     * Get if the version of Minecraft the server is running is equal to the specified version.
     *
     * @param version The version to check
     * @return If the version of Minecraft the server is running is equal to the specified version
     */
    default boolean is(MinecraftVersion version) {
        return this == version;
    }

    /**
     * Get if the version of Minecraft the server is running is within the defined range.
     *
     * @param startInclusive The start of the range
     * @param start The start of the range
     * @param endInclusive The end of the range
     * @param end The end of the range
     */
    default boolean isInRange(
            boolean startInclusive,
            MinecraftVersion start,
            boolean endInclusive,
            MinecraftVersion end) {
        MinecraftVersion[] VERSIONS = MinecraftVersions.Cache.versions();
        if (start == null) {
            start = VERSIONS[0];
        }
        if (end == null) {
            end = VERSIONS[VERSIONS.length - 1];
        }
        return (startInclusive ? this.ord() >= start.ord() : this.ord() > start.ord())
                && (endInclusive ? this.ord() <= end.ord() : this.ord() < end.ord());
    }

    /**
     * Get if the version of Minecraft the server is running is within the defined range. Assumed to
     * be an inclusive range.
     *
     * @param start The start of the range
     * @param end The end of the range
     */
    default boolean isInRange(MinecraftVersion start, MinecraftVersion end) {
        return this.isInRange(true, start, true, end);
    }

    /**
     * Get if the version of Minecraft the server is running is within the defined range. <br>
     * Strings are read in the format of: <b>(1.17,1.20]</b> or <b>[1.17,)</b> or <b>(,1.20]</b>
     *
     * @param rangeString The range to check
     * @return If the version of Minecraft the server is running is within the defined range
     */
    default boolean parseRange(String rangeString) {
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
        if (rangeString.charAt(0) == ',') {
            start = null;
            end = MinecraftVersions.of(rangeString.substring(1));
        } else if (rangeString.charAt(rangeString.length() - 1) == ',') {
            start = MinecraftVersions.of(rangeString.substring(0, rangeString.length() - 1));
            end = null;
        } else {
            String[] range = rangeString.split(",");
            start = MinecraftVersions.of(range[0]);
            end = MinecraftVersions.of(range[1]);
        }
        return this.isInRange(startInclusive, start, endInclusive, end);
    }

    /**
     * Get if the version of Minecraft the server is running is older than the specified version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is older.
     */
    default boolean isNewerThan(MinecraftVersion version) {
        return this.ord() > version.ord();
    }

    /**
     * Get if the version of Minecraft the server is running is older than the specified version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is older.
     */
    default boolean isNewerThan(String version) {
        return this.ord() > MinecraftVersions.of(version).ord();
    }

    /**
     * Get if the version of Minecraft the server is running is equal to or newer than the specified
     * version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is equal to or newer.
     */
    default boolean isAtLeast(MinecraftVersion version) {
        return this.ord() >= version.ord();
    }

    /**
     * Get if the version of Minecraft the server is running is equal to or newer than the specified
     * version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is equal to or newer.
     */
    default boolean isAtLeast(String version) {
        return this.ord() >= MinecraftVersions.of(version).ord();
    }

    /**
     * Get if the version of Minecraft the server is running is newer than the specified version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is newer.
     */
    default boolean isOlderThan(MinecraftVersion version) {
        return this.ord() < version.ord();
    }

    /**
     * Get if the version of Minecraft the server is running is newer than the specified version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is newer.
     */
    default boolean isOlderThan(String version) {
        return this.ord() < MinecraftVersions.of(version).ord();
    }

    /**
     * Get if the version of Minecraft the server is running is equal to or older than the specified
     * version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is equal to or older.
     */
    default boolean isAtMost(MinecraftVersion version) {
        return this.ord() <= version.ord();
    }

    /**
     * Get if the version of Minecraft the server is running is equal to or older than the specified
     * version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is equal to or older.
     */
    default boolean isAtMost(String version) {
        return this.ord() <= MinecraftVersions.of(version).ord();
    }
}