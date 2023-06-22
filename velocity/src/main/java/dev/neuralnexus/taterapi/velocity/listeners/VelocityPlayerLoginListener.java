package dev.neuralnexus.taterapi.velocity.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

public class VelocityPlayerLoginListener {
    @Subscribe
    public void onPlayerLogin(LoginEvent event) {
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
