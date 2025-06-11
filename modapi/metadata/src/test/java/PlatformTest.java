/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.neuralnexus.taterapi.meta.Platforms;

import org.junit.jupiter.api.Test;

public class PlatformTest {
    //    private static final String PLATFORM_STRING_NAME = "bukkit";
    //
    //    @Test
    //    public void testFromString() {
    //        assertEquals(Platform.BUKKIT, Platform.from(PLATFORM_STRING_NAME));
    //    }

    /** The dev environment should have no platforms. */
    @Test
    public void testToString() {
        assertEquals(0, Platforms.get().size());
    }
}
