/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi;

import dev.neuralnexus.taterapi.metadata.impl.PlatformDataImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Represents the version of Minecraft the server is running. */
public enum MinecraftVersion {
    // Pre-Classic
    RD_132211("rd-132211"),
    RD_132328("rd-132328"),
    RD_20090515("rd-20090515"),
    RD_160052("rd-160052"),
    RD_161348("rd-161348"),

    // Classic
    C0_0_11A("c0.0.11a"),
    C0_0_13A_03("c0.0.13a_03"),
    C0_0_13A("c0.0.13a"),
    C0_30_01C("c0.30_01c"),

    // Indev
    INF_20100618("inf-20100618"),

    // Alpha
    A1_0_4("a1.0.4"),
    A1_0_5_01("a1.0.5_01"),
    A1_0_11("a1.0.11"),
    A1_0_14("a1.0.14"),
    A1_0_15("a1.0.15"),
    A1_0_16("a1.0.16"),
    A1_0_17_02("a1.0.17_02"),
    A1_0_17_04("a1.0.17_04"),
    A1_1_0("a1.1.0"),
    A1_1_2("a1.1.2"),
    A1_1_2_01("a1.1.2_01"),
    A1_2_0("a1.2.0"),
    A1_2_0_01("a1.2.0_01"),
    A1_2_0_02("a1.2.0_02"),
    A1_2_1("a1.2.1"),
    A1_2_1_01("a1.2.1_01"),
    A1_2_2A("a1.2.2a"),
    A1_2_2B("a1.2.2b"),
    A1_2_3("a1.2.3"),
    A1_2_3_01("a1.2.3_01"),
    A1_2_3_02("a1.2.3_02"),
    A1_2_3_04("a1.2.3_04"),
    A1_2_4_01("a1.2.4_01"),
    A1_2_5("a1.2.5"),
    A1_2_6("a1.2.6"),

    // Beta
    B1_0("b1.0"),
    B1_0_01("b1.0_01"),
    B1_0_2("b1.0.2"),
    B1_1_01("b1.1_01"),
    B1_1_02("b1.1_02"),
    B1_2("b1.2"),
    B1_2_01("b1.2_01"),
    B1_2_02("b1.2_02"),
    B1_3B("b1.3b"),
    B1_3_01("b1.3_01"),
    B1_4("b1.4"),
    B1_4_01("b1.4_01"),
    B1_5("b1.5"),
    B1_5_01("b1.5_01"),
    B1_6("b1.6"),
    B1_6_1("b1.6.1"),
    B1_6_2("b1.6.2"),
    B1_6_3("b1.6.3"),
    B1_6_4("b1.6.4"),
    B1_6_5("b1.6.5"),
    B1_6_6("b1.6.6"),
    B1_7("b1.7"),
    B1_7_2("b1.7.2"),
    B1_7_3("b1.7.3"),
    B1_8("b1.8"),
    B1_8_1("b1.8.1"),

    // Release
    V1_0("1.0"),
    V1_1("1.1"),
    V1_2_1("1.2.1"),
    V1_2_2("1.2.2"),
    V1_2_3("1.2.3"),
    V1_2_4("1.2.4"),
    V1_2_5("1.2.5"),
    V1_3_1("1.3.1"),
    V1_3_2("1.3.2"),
    V1_4_2("1.4.2"),
    V1_4_4("1.4.4"),
    V1_4_5("1.4.5"),
    V1_4_6("1.4.6"),
    V1_4_7("1.4.7"),
    V1_5_1("1.5.1"),
    V1_5_2("1.5.2"),
    V1_6_1("1.6.1"),
    V1_6_2("1.6.2"),
    V1_6_4("1.6.4"),
    V1_7_2("1.7.2"),
    V1_7_3("1.7.3"),
    V1_7_4("1.7.4"),
    V1_7_5("1.7.5"),
    V1_7_6("1.7.6"),
    V1_7_7("1.7.7"),
    V1_7_8("1.7.8"),
    V1_7_9("1.7.9"),
    V1_7_10("1.7.10"),
    V1_8("1.8"),
    V1_8_1("1.8.1"),
    V1_8_2("1.8.2"),
    V1_8_3("1.8.3"),
    V1_8_4("1.8.4"),
    V1_8_5("1.8.5"),
    V1_8_6("1.8.6"),
    V1_8_7("1.8.7"),
    V1_8_8("1.8.8"),
    V1_8_9("1.8.9"),
    V1_9("1.9"),
    V1_9_1("1.9.1"),
    V1_9_2("1.9.2"),
    V1_9_3("1.9.3"),
    V1_9_4("1.9.4"),
    V1_10("1.10"),
    V1_10_1("1.10.1"),
    V1_10_2("1.10.2"),
    V1_11("1.11"),
    V1_11_1("1.11.1"),
    V1_11_2("1.11.2"),
    V1_12("1.12"),
    V1_12_1("1.12.1"),
    V1_12_2("1.12.2"),
    V1_13("1.13"),
    V1_13_1("1.13.1"),
    V1_13_2("1.13.2"),
    V1_14("1.14"),
    V1_14_1("1.14.1"),
    V1_14_2("1.14.2"),
    V1_14_3("1.14.3"),
    V1_14_4("1.14.4"),
    V1_15("1.15"),
    V1_15_1("1.15.1"),
    V1_15_2("1.15.2"),
    V1_16("1.16"),
    V1_16_1("1.16.1"),
    V1_16_2("1.16.2"),
    V1_16_3("1.16.3"),
    V1_16_4("1.16.4"),
    V1_16_5("1.16.5"),
    V1_17("1.17"),
    V1_17_1("1.17.1"),
    V1_18("1.18"),
    V1_18_1("1.18.1"),
    V1_18_2("1.18.2"),
    V1_19("1.19"),
    V1_19_1("1.19.1"),
    V1_19_2("1.19.2"),
    V1_19_3("1.19.3"),
    V1_19_4("1.19.4"),
    V1_20("1.20"),
    V1_20_1("1.20.1"),
    V1_20_2("1.20.2"),
    V1_20_3("1.20.3"),
    V1_20_4("1.20.4"),
    V1_20_5("1.20.5"),
    V1_20_6("1.20.6"),
    V1_21("1.21"),
    UNKNOWN("Unknown");

    private static MinecraftVersion minecraftVersion = UNKNOWN;
    private final String version;

    MinecraftVersion(String version) {
        this.version = version;
    }

    /** Get the version of Minecraft the server is running. */
    public static MinecraftVersion get() {
        if (minecraftVersion == UNKNOWN) {
            minecraftVersion = new PlatformDataImpl().minecraftVersion();
        }
        return minecraftVersion;
    }

    /**
     * Create a MinecraftVersion from a string.
     *
     * @param version The version to create
     * @return The MinecraftVersion
     */
    public static MinecraftVersion from(String version) {
        Stream<MinecraftVersion> values = Arrays.stream(MinecraftVersion.values());
        if (!version.toLowerCase().contains("a1") && !version.toLowerCase().contains("b1")) {
            values = values.sorted((o1, o2) -> o2.ordinal() - o1.ordinal());
        }
        if (version.contains("1.1.8 (MC: 1.7.3)")) {
            return B1_7_3;
        }
        return values.filter(v -> version.contains(v.toString())).findFirst().orElse(UNKNOWN);
    }

    /**
     * Get a set of all the enums.
     *
     * @return A set of all the enums
     */
    public static List<String> getValues() {
        return Arrays.stream(MinecraftVersion.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    /**
     * Check if the specified version is a valid Minecraft version.
     *
     * @param version The version to check
     * @return If the specified version is a valid Minecraft version
     */
    public static boolean contains(String version) {
        return getValues().contains(version);
    }

    /**
     * Utility to get the version delimiter for working with TaterLib's NMS. <br>
     * Eg. <b>1.17.1</b> into <b>v1_17_1</b>
     *
     * @return The version of Minecraft the server is running
     */
    public String getDelimiterString() {
        return this.name().toLowerCase().replace(".", "_");
    }

    /**
     * Get the version of Minecraft the server is running.
     *
     * @return The version of Minecraft the server is running
     */
    @Override
    public String toString() {
        return this.version;
    }

    /**
     * Get if the version of Minecraft the server is running is equal to the specified version.
     *
     * @param version The version to check
     * @return If the version of Minecraft the server is running is equal to the specified version
     */
    public boolean is(String version) {
        return this.version.equals(version);
    }

    /**
     * Get if the version of Minecraft the server is running is equal to the specified version.
     *
     * @param version The version to check
     * @return If the version of Minecraft the server is running is equal to the specified version
     */
    public boolean is(MinecraftVersion version) {
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
    public boolean isInRange(
            boolean startInclusive,
            MinecraftVersion start,
            boolean endInclusive,
            MinecraftVersion end) {
        if (start == null) {
            start = MinecraftVersion.values()[0];
        }
        if (end == null) {
            end = MinecraftVersion.values()[MinecraftVersion.values().length - 1];
        }
        return (startInclusive
                        ? this.ordinal() >= start.ordinal()
                        : this.ordinal() > start.ordinal())
                && (endInclusive
                        ? this.ordinal() <= end.ordinal()
                        : this.ordinal() < end.ordinal());
    }

    /**
     * Get if the version of Minecraft the server is running is within the defined range. Assumed to
     * be an inclusive range.
     *
     * @param start The start of the range
     * @param end The end of the range
     */
    public boolean isInRange(MinecraftVersion start, MinecraftVersion end) {
        return this.isInRange(true, start, true, end);
    }

    /**
     * Get if the version of Minecraft the server is running is within the defined range. <br>
     * Strings are read in the format of: <b>(1.17,1.20]</b> or <b>[1.17,)</b> or <b>(,1.20]</b>
     *
     * @param rangeString The range to check
     * @return If the version of Minecraft the server is running is within the defined range
     */
    public boolean parseRange(String rangeString) {
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
            end = MinecraftVersion.from(rangeString.substring(1));
        } else if (rangeString.charAt(rangeString.length() - 1) == ',') {
            start = MinecraftVersion.from(rangeString.substring(0, rangeString.length() - 1));
            end = null;
        } else {
            String[] range = rangeString.split(",");
            start = MinecraftVersion.from(range[0]);
            end = MinecraftVersion.from(range[1]);
        }
        return this.isInRange(startInclusive, start, endInclusive, end);
    }

    /**
     * Get if the version of Minecraft the server is running is older than the specified version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is older.
     */
    public boolean isNewerThan(MinecraftVersion version) {
        return this.ordinal() > version.ordinal();
    }

    /**
     * Get if the version of Minecraft the server is running is older than the specified version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is older.
     */
    public boolean isNewerThan(String version) {
        return this.ordinal() > MinecraftVersion.from(version).ordinal();
    }

    /**
     * Get if the version of Minecraft the server is running is equal to or newer than the specified
     * version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is equal to or newer.
     */
    public boolean isAtLeast(MinecraftVersion version) {
        return this.ordinal() >= version.ordinal();
    }

    /**
     * Get if the version of Minecraft the server is running is equal to or newer than the specified
     * version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is equal to or newer.
     */
    public boolean isAtLeast(String version) {
        return this.ordinal() >= MinecraftVersion.from(version).ordinal();
    }

    /**
     * Get if the version of Minecraft the server is running is newer than the specified version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is newer.
     */
    public boolean isOlderThan(MinecraftVersion version) {
        return this.ordinal() < version.ordinal();
    }

    /**
     * Get if the version of Minecraft the server is running is newer than the specified version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is newer.
     */
    public boolean isOlderThan(String version) {
        return this.ordinal() < MinecraftVersion.from(version).ordinal();
    }

    /**
     * Get if the version of Minecraft the server is running is equal to or older than the specified
     * version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is equal to or older.
     */
    public boolean isAtMost(MinecraftVersion version) {
        return this.ordinal() <= version.ordinal();
    }

    /**
     * Get if the version of Minecraft the server is running is equal to or older than the specified
     * version.
     *
     * @param version The version to check.
     * @return If the Minecraft version is equal to or older.
     */
    public boolean isAtMost(String version) {
        return this.ordinal() <= MinecraftVersion.from(version).ordinal();
    }
}
