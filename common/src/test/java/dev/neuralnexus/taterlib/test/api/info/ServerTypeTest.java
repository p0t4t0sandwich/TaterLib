package dev.neuralnexus.taterlib.test.api.info;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.neuralnexus.taterlib.api.info.ServerType;

import org.junit.jupiter.api.Test;

/** Tests for the ServerType class. */
public class ServerTypeTest {
    private static final String PLATFORM_STRING_NAME = "bukkit";

    @Test
    public void testFromString() {
        assertEquals(ServerType.BUKKIT, ServerType.from(PLATFORM_STRING_NAME));
    }

    /**
     * Should always return {@link ServerType#UNKNOWN} as the server type is not available in the
     * test environment.
     */
    @Test
    public void testToString() {
        assertEquals(ServerType.UNKNOWN, ServerType.getServerType());
    }
}
