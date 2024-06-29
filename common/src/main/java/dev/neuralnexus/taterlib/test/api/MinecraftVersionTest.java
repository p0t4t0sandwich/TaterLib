/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.test.api;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
//
// import dev.neuralnexus.taterlib.api.MinecraftVersion;
//
// import org.junit.jupiter.api.Test;

/** Tests for the MinecraftVersion class. */
public class MinecraftVersionTest {
    private static final String REGULAR_VERSION_STRING = "1.16.5";
    private static final String PREFIXED_VERSION_STRING = "someprefix1.20";
    private static final String SUFFIXED_VERSION_STRING = "b1.8somesuffix";
    private static final String PROJECT_POSIDON_STRING = "1.1.8 (MC: 1.7.3)";
    private static final String REGULAR_RANGE_STRING = "(1.17,1.20]";
    private static final String OPEN_START_RANGE_STRING = "(,1.20]";
    private static final String OPEN_END_RANGE_STRING = "[1.17,)";

    //    @Test
    //    public void testFromString() {
    //        Assertions.assertEquals(MinecraftVersion.V1_16_5,
    // MinecraftVersion.from(REGULAR_VERSION_STRING));
    //        Assertions.assertEquals(MinecraftVersion.V1_20,
    // MinecraftVersion.from(PREFIXED_VERSION_STRING));
    //        Assertions.assertEquals(MinecraftVersion.B1_8,
    // MinecraftVersion.from(SUFFIXED_VERSION_STRING));
    //        Assertions.assertEquals(MinecraftVersion.B1_7_3,
    // MinecraftVersion.from(PROJECT_POSIDON_STRING));
    //    }
    //
    //    @Test
    //    public void testParseRange() {
    //        Assertions.assertEquals(
    //                MinecraftVersion.V1_18.isInRange(
    //                        false, MinecraftVersion.V1_17, true, MinecraftVersion.V1_20),
    //                MinecraftVersion.V1_18.parseRange(REGULAR_RANGE_STRING));
    //        Assertions.assertEquals(
    //                MinecraftVersion.V1_18.isInRange(false, null, true, MinecraftVersion.V1_20),
    //                MinecraftVersion.V1_18.parseRange(OPEN_START_RANGE_STRING));
    //        Assertions.assertEquals(
    //                MinecraftVersion.V1_18.isInRange(true, MinecraftVersion.V1_17, false, null),
    //                MinecraftVersion.V1_18.parseRange(OPEN_END_RANGE_STRING));
    //        Assertions.assertTrue(MinecraftVersion.V1_16_5.parseRange(REGULAR_VERSION_STRING));
    //    }
}
