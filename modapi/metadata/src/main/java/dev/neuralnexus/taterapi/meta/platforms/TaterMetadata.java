package dev.neuralnexus.taterapi.meta.platforms;

import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platform;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class TaterMetadata {
    private TaterMetadata() {}

    public static void init(Platform platform) {
        MetaAPI api = MetaAPI.instance();
        try {
            api.primaryPlatform();
        } catch (MetaAPI.NoPrimaryPlatformException e) {
            api.setPrimaryPlatform(platform);
        }
    }
}
