package dev.neuralnexus.conditionalmixins.api;

import dev.neuralnexus.conditionalmixins.api.bootstrap.BootstrapData;
import dev.neuralnexus.conditionalmixins.api.bootstrap.InitData;

public class CondMixinsAPI {
    private static final CondMixinsAPI instance = new CondMixinsAPI();
    private BootstrapData bootstrapData;

    public static CondMixinsAPI get() {
        if (instance.bootstrapData == null) {
            instance.bootstrapData = InitData.initBootstrap();
        }
        return instance;
    }


}
