/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.version;

import static dev.neuralnexus.modapi.metadata.impl.util.FlexVerComparator.compare;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/** Interface for comparing versions */
public interface VersionComparable<T extends VersionComparable<?>> extends Comparable<T> {
    /**
     * Get the version as a string
     *
     * @return The version as a string
     */
    String version();

    @Override
    default int compareTo(@NotNull T o) {
        return compare(this.version(), o.version());
    }

    /**
     * Check if the version is equal to another version
     *
     * @param version The version to compare
     * @return True if the versions are equal
     * @throws NullPointerException if version is null
     */
    default boolean is(@NotNull String version) throws NullPointerException {
        Objects.requireNonNull(version, "version cannot be null");
        return compare(this.version(), version) == 0;
    }

    /**
     * Check if the version is equal to another version
     *
     * @param version The version to compare
     * @return True if the versions are equal
     * @throws NullPointerException if version is null
     */
    default boolean is(@NotNull T version) throws NullPointerException {
        Objects.requireNonNull(version, "version cannot be null");
        return compare(this.version(), version.version()) == 0;
    }

    /**
     * Check if the version is in the range of two other versions
     *
     * @param startInclusive Whether the start version is inclusive
     * @param start The start version
     * @param endInclusive Whether the end version is inclusive
     * @param end The end version
     * @return True if the version is in the range
     * @throws NullPointerException if start or end is null
     */
    default boolean isInRange(
            boolean startInclusive,
            @NotNull String start,
            boolean endInclusive,
            @NotNull String end)
            throws NullPointerException {
        Objects.requireNonNull(start, "start cannot be null");
        Objects.requireNonNull(end, "end cannot be null");
        return (startInclusive
                        ? compare(this.version(), start) >= 0
                        : compare(this.version(), start) > 0)
                && (endInclusive
                        ? compare(this.version(), end) <= 0
                        : compare(this.version(), end) < 0);
    }

    /**
     * Check if the version is in the range of two other versions
     *
     * @param startInclusive Whether the start version is inclusive
     * @param start The start version
     * @param endInclusive Whether the end version is inclusive
     * @param end The end version
     * @return True if the version is in the range
     * @throws NullPointerException if start or end is null
     */
    default boolean isInRange(
            boolean startInclusive, @NotNull T start, boolean endInclusive, @NotNull T end)
            throws NullPointerException {
        Objects.requireNonNull(start, "start cannot be null");
        Objects.requireNonNull(end, "end cannot be null");
        return (startInclusive
                        ? compare(this.version(), start.version()) >= 0
                        : compare(this.version(), start.version()) > 0)
                && (endInclusive
                        ? compare(this.version(), end.version()) <= 0
                        : compare(this.version(), end.version()) < 0);
    }

    /**
     * Check if the version is in the range of two other versions
     *
     * @param start The start version
     * @param end The end version
     * @return True if the version is in the range
     * @throws NullPointerException if start or end is null
     */
    default boolean isInRange(@NotNull String start, @NotNull String end)
            throws NullPointerException {
        Objects.requireNonNull(start, "start cannot be null");
        Objects.requireNonNull(end, "end cannot be null");
        return compare(this.version(), start) >= 0 && compare(this.version(), end) <= 0;
    }

    /**
     * Check if the version is in the range of two other versions
     *
     * @param start The start version
     * @param end The end version
     * @return True if the version is in the range
     * @throws NullPointerException if start or end is null
     */
    default boolean isInRange(@NotNull T start, @NotNull T end) throws NullPointerException {
        Objects.requireNonNull(start, "start cannot be null");
        Objects.requireNonNull(end, "end cannot be null");
        return compare(this.version(), start.version()) >= 0
                && compare(this.version(), end.version()) <= 0;
    }

    /**
     * Get if The version of Minecraft the server is running is within the defined range. <br>
     * Strings are read in the format of: <b>(1.17,1.20]</b> or <b>[1.17,)</b> or <b>(,1.20]</b>
     *
     * @param rangeString The range to check
     * @return If The version of Minecraft the server is running is within the defined range
     * @throws NullPointerException If the range string is null
     */
    boolean parseRange(@NotNull String rangeString) throws NullPointerException;

    /**
     * Check if the version is newer than another version
     *
     * @param version The version to compare
     * @return True if the version is newer
     * @throws NullPointerException if version is null
     */
    default boolean isNewerThan(@NotNull String version) throws NullPointerException {
        Objects.requireNonNull(version, "version cannot be null");
        return compare(this.version(), version) > 0;
    }

    /**
     * Check if the version is newer than another version
     *
     * @param version The version to compare
     * @return True if the version is newer
     * @throws NullPointerException if version is null
     */
    default boolean isNewerThan(@NotNull T version) throws NullPointerException {
        Objects.requireNonNull(version, "version cannot be null");
        return compare(this.version(), version.version()) > 0;
    }

    /**
     * Check if the version is at least another version
     *
     * @param version The version to compare
     * @return True if the version is at least
     * @throws NullPointerException if version is null
     */
    default boolean isAtLeast(@NotNull String version) throws NullPointerException {
        Objects.requireNonNull(version, "version cannot be null");
        return compare(this.version(), version) >= 0;
    }

    /**
     * Check if the version is at least another version
     *
     * @param version The version to compare
     * @return True if the version is at least
     * @throws NullPointerException if version is null
     */
    default boolean isAtLeast(@NotNull T version) throws NullPointerException {
        Objects.requireNonNull(version, "version cannot be null");
        return compare(this.version(), version.version()) >= 0;
    }

    /**
     * Check if the version is older than another version
     *
     * @param version The version to compare
     * @return True if the version is older
     * @throws NullPointerException if version is null
     */
    default boolean isOlderThan(@NotNull String version) throws NullPointerException {
        Objects.requireNonNull(version, "version cannot be null");
        return compare(this.version(), version) < 0;
    }

    /**
     * Check if the version is older than another version
     *
     * @param version The version to compare
     * @return True if the version is older
     * @throws NullPointerException if version is null
     */
    default boolean isOlderThan(@NotNull T version) throws NullPointerException {
        Objects.requireNonNull(version, "version cannot be null");
        return compare(this.version(), version.version()) < 0;
    }

    /**
     * Check if the version is at most another version
     *
     * @param version The version to compare
     * @return True if the version is at most
     * @throws NullPointerException if version is null
     */
    default boolean isAtMost(@NotNull String version) throws NullPointerException {
        Objects.requireNonNull(version, "version cannot be null");
        return compare(this.version(), version) <= 0;
    }

    /**
     * Check if the version is at most another version
     *
     * @param version The version to compare
     * @return True if the version is at most
     * @throws NullPointerException if version is null
     */
    default boolean isAtMost(@NotNull T version) throws NullPointerException {
        Objects.requireNonNull(version, "version cannot be null");
        return compare(this.version(), version.version()) <= 0;
    }
}
