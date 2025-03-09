/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge;

import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onEnable() {
        TaterLib.start();
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.SPONGE)) {
            // Register listeners
            //            PluginContainer container = (PluginContainer) Loader.instance().plugin();
            //            EventManager eventManager = Sponge.eventManager();
            //            eventManager.registerListeners(container, new SpongeBlockListener());
            //            eventManager.registerListeners(container, new SpongeCommandListener());
            //            eventManager.registerListeners(container, new SpongeEntityListener());
            //            eventManager.registerListeners(container, new SpongePlayerListener());
            //            eventManager.registerListeners(container, new
            // SpongePlayerLogoutListener());
            //            eventManager.registerListeners(container, new
            // SpongePlayerMessageListener());
            //            eventManager.registerListeners(container, new SpongeServerListener());
        }
    }
}
