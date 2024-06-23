package dev.neuralnexus.taterlib.test.config;

// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import dev.neuralnexus.taterlib.api.MinecraftVersion;
// import dev.neuralnexus.taterlib.api.Platform;
// import dev.neuralnexus.taterlib.config.sections.MixinConfig;
//
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.HashSet;
// import java.util.Set;

/** Tests for the MixinConfig class. */
public class MixinConfigTest {
    //    private static final MixinConfig simpleMixinConfig = new MixinConfigAdapter("test", true);
    //    private static final MixinConfig otherSimpleMixinConfig = new MixinConfigAdapter("test",
    // false);
    //    private static final MixinConfig complexMixinConfig =
    //            new MixinConfigAdapter(
    //                    "test",
    //                    true,
    //                    new HashSet<>(Arrays.asList("1.16.5", "!1.17")),
    //                    new HashSet<>(Arrays.asList("bukkit", "!fabric")),
    //                    new HashSet<>(Arrays.asList("test2", "!test3")));
    //
    //    public void testIsMixinEnabled() {
    //        Assertions.assertTrue(simpleMixinConfig.enabled());
    //        Assertions.assertFalse(otherSimpleMixinConfig.enabled());
    //        Assertions.assertTrue(complexMixinConfig.enabled());
    //    }
    //
    //    public void testCheckVersions() {
    //        Assertions.assertTrue(simpleMixinConfig.checkVersions(MinecraftVersion.B1_0));
    //        Assertions.assertTrue(simpleMixinConfig.checkVersions(MinecraftVersion.V1_16_5));
    //        Assertions.assertFalse(complexMixinConfig.checkVersions(MinecraftVersion.V1_17));
    //    }
    //
    //    public void testCheckServerTypes() {
    //        Assertions.assertTrue(simpleMixinConfig.checkPlatforms(Platform.BUKKIT));
    //        Assertions.assertTrue(complexMixinConfig.checkPlatforms(Platform.BUKKIT));
    //        Assertions.assertFalse(complexMixinConfig.checkPlatforms(Platform.FABRIC));
    //    }
    //
    //    public void testCheckDepends() {
    //        Assertions.assertTrue(simpleMixinConfig.checkDepends(Collections.emptySet()));
    //
    // Assertions.assertTrue(complexMixinConfig.checkDepends(Collections.singleton("test2")));
    //
    // Assertions.assertFalse(complexMixinConfig.checkDepends(Collections.singleton("test3")));
    //    }

    //    static class MixinConfigAdapter extends MixinConfig {
    //        private final Set<String> versions = new HashSet<>();
    //        private final Set<String> serverTypes = new HashSet<>();
    //        private final Set<String> depends = new HashSet<>();
    //        private final String name;
    //        private final boolean enabled;
    //
    //        public MixinConfigAdapter(String name, boolean enabled) {
    //            this.name = name;
    //            this.enabled = enabled;
    //        }
    //
    //        public MixinConfigAdapter(
    //                String name,
    //                boolean enabled,
    //                Set<String> versions,
    //                Set<String> serverTypes,
    //                Set<String> depends) {
    //            this.name = name;
    //            this.enabled = enabled;
    //            this.versions.addAll(versions);
    //            this.serverTypes.addAll(serverTypes);
    //            this.depends.addAll(depends);
    //        }
    //    }
}
