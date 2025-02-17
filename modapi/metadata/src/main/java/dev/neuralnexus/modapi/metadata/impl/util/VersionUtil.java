/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.util;

import static dev.neuralnexus.modapi.metadata.impl.util.FlexVerComparator.compare;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/** Utility class for version parsing */
public class VersionUtil {
    /**
     * A parsed range object
     *
     * @param startInclusive Whether the start version is inclusive
     * @param start The start version
     * @param end The end version
     * @param endInclusive Whether the end version is inclusive
     */
    public record Range(
            boolean startInclusive,
            @Nullable String start,
            @Nullable String end,
            boolean endInclusive) {
        /**
         * Parse a range string
         *
         * @param rangeString The range string
         * @return The parsed range
         */
        public static @Nullable VersionUtil.Range parse(@NotNull String rangeString) {
            Objects.requireNonNull(rangeString, "Range string cannot be null");
            rangeString = rangeString.trim();
            boolean startInclusive;
            boolean endInclusive;
            String start;
            String end;
            if (rangeString.startsWith("[")) {
                startInclusive = true;
            } else if (rangeString.startsWith("(")) {
                startInclusive = false;
            } else {
                return null;
            }
            if (rangeString.endsWith("]")) {
                endInclusive = true;
            } else if (rangeString.endsWith(")")) {
                endInclusive = false;
            } else {
                return null;
            }
            rangeString = rangeString.substring(1, rangeString.length() - 1);
            if (rangeString.startsWith(",")) {
                start = null;
                end = rangeString.substring(1);
            } else if (rangeString.endsWith(",")) {
                start = rangeString.substring(0, rangeString.length() - 1);
                end = null;
            } else {
                String[] split = rangeString.split(",");
                if (split.length != 2) {
                    return null;
                }
                start = split[0];
                end = split[1];
            }
            return new Range(startInclusive, start, end, endInclusive);
        }

        /**
         * Check if the version is in the range
         *
         * @param version The version to check
         * @return True if the version is in the range
         */
        public boolean resolve(@NotNull String version) {
            Objects.requireNonNull(version, "version cannot be null");
            Objects.requireNonNull(start, "start cannot be null");
            Objects.requireNonNull(end, "end cannot be null");
            return (startInclusive ? compare(version, start) >= 0 : compare(version, start) > 0)
                    && (endInclusive ? compare(version, end) <= 0 : compare(version, end) < 0);
        }
    }
}
