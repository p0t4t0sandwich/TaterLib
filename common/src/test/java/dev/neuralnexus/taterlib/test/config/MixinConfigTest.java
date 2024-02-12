package dev.neuralnexus.taterlib.test.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.config.sections.MixinConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/** Tests for the MixinConfig class. */
public class MixinConfigTest {
    private static final MixinConfig simpleMixinConfig = new MixinConfig("test", true);
    private static final MixinConfig otherSimpleMixinConfig = new MixinConfig("test", false);
    private static final MixinConfig complexMixinConfig =
            new MixinConfig(
                    "test",
                    true,
                    new HashSet<>(Arrays.asList("1.16.5", "!1.17")),
                    new HashSet<>(Arrays.asList("bukkit", "!fabric")),
                    new HashSet<>(Arrays.asList("test2", "!test3")));

    public void testIsMixinEnabled() {
        assertTrue(simpleMixinConfig.enabled());
        assertFalse(otherSimpleMixinConfig.enabled());
        assertTrue(complexMixinConfig.enabled());
    }

    public void testCheckVersions() {
        assertTrue(simpleMixinConfig.checkVersions(MinecraftVersion.B1_0));
        assertTrue(simpleMixinConfig.checkVersions(MinecraftVersion.V1_16_5));
        assertFalse(complexMixinConfig.checkVersions(MinecraftVersion.V1_17));
    }

    public void testCheckServerTypes() {
        assertTrue(simpleMixinConfig.checkServerTypes(ServerType.BUKKIT));
        assertTrue(complexMixinConfig.checkServerTypes(ServerType.BUKKIT));
        assertFalse(complexMixinConfig.checkServerTypes(ServerType.FABRIC));
    }

    public void testCheckDepends() {
        assertTrue(simpleMixinConfig.checkDepends(Collections.emptySet()));
        assertTrue(complexMixinConfig.checkDepends(Collections.singleton("test2")));
        assertFalse(complexMixinConfig.checkDepends(Collections.singleton("test3")));
    }
}
