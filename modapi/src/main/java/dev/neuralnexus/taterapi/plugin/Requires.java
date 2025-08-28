package dev.neuralnexus.taterapi.plugin;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

public @interface Requires {
    Version[] version();
    @interface Version {
        MinecraftVersion[] value() default {};
        MinecraftVersion[] not() default {};
        MinecraftVersion min() default MinecraftVersion.UNKNOWN;
        MinecraftVersion max() default MinecraftVersion.UNKNOWN;
    }

    Platform[] platform();
    @interface Platform {
        dev.neuralnexus.taterapi.meta.enums.Platform[] value() default {};
        dev.neuralnexus.taterapi.meta.enums.Platform[] not() default {};
    }

    Mappings mappings() default Mappings.NONE;

//    @interface Dependency {
//        String value();
//        VersionComparable version() default null;
//    }
}
