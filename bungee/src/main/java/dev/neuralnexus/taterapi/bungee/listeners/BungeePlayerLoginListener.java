package dev.neuralnexus.taterapi.bungee.listeners;

import dev.neuralnexus.taterapi.bungee.BungeeMain;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

public class BungeePlayerLoginListener implements Listener {
    BungeeMain plugin = BungeeMain.getInstance();

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        runTaskAsync(() -> {
            try {
                // Do stuff
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
