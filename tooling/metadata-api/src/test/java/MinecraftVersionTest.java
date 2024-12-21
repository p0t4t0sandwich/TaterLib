/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;

import org.junit.jupiter.api.Test;

/** Tests for the MinecraftVersion abstraction. */
public class MinecraftVersionTest {
    private static final String REGULAR_VERSION_STRING = "1.16.5";
    private static final String PREFIXED_VERSION_STRING = "someprefix1.20";
    private static final String SUFFIXED_VERSION_STRING = "b1.8somesuffix";
    private static final String PROJECT_POSIDON_STRING = "1.1.8 (MC: 1.7.3)";
    private static final String REGULAR_RANGE_STRING = "(1.17,1.20]";
    private static final String OPEN_START_RANGE_STRING = "(,1.20]";
    private static final String OPEN_END_RANGE_STRING = "[1.17,)";

    @Test
    public void testFromString() {
        assertEquals(MinecraftVersions.V16_5, MinecraftVersion.of(REGULAR_VERSION_STRING));
        assertEquals(MinecraftVersions.V20, MinecraftVersion.of(PREFIXED_VERSION_STRING));
        assertEquals(MinecraftVersions.B1_8, MinecraftVersion.of(SUFFIXED_VERSION_STRING));
        assertEquals(MinecraftVersions.B1_7_3, MinecraftVersion.of(PROJECT_POSIDON_STRING));
    }

    @Test
    public void testParseRange() {
        assertEquals(
                MinecraftVersions.V18.isInRange(
                        false, MinecraftVersions.V17, true, MinecraftVersions.V20),
                MinecraftVersions.V18.parseRange(REGULAR_RANGE_STRING));
        assertEquals(
                MinecraftVersions.V18.isInRange(false, null, true, MinecraftVersions.V20),
                MinecraftVersions.V18.parseRange(OPEN_START_RANGE_STRING));
        assertEquals(
                MinecraftVersions.V18.isInRange(true, MinecraftVersions.V17, false, null),
                MinecraftVersions.V18.parseRange(OPEN_END_RANGE_STRING));
        assertTrue(MinecraftVersions.V16_5.parseRange(REGULAR_VERSION_STRING));
    }
}
