package dev.neuralnexus.taterlib.test.api.info;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;

import org.junit.jupiter.api.Test;

/** Tests for the MinecraftVersion class. */
public class MinecraftVersionTest {
    private static final String REGULAR_VERSION_STRING = "1.16.5";
    private static final String PREFIXED_VERSION_STRING = "someprefix1.20";
    private static final String SUFFIXED_VERSION_STRING = "b1.8somesuffix";

    @Test
    public void testFromString() {
        assertEquals(MinecraftVersion.V1_16_5, MinecraftVersion.from(REGULAR_VERSION_STRING));
        assertEquals(MinecraftVersion.V1_20, MinecraftVersion.from(PREFIXED_VERSION_STRING));
        assertEquals(MinecraftVersion.B1_8, MinecraftVersion.from(SUFFIXED_VERSION_STRING));
    }
}
