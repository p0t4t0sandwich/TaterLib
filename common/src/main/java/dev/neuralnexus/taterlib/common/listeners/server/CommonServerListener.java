package dev.neuralnexus.taterlib.common.listeners.server;

import static dev.neuralnexus.taterlib.common.Utils.runTaskAsync;

public class CommonServerListener {
    /**
     * Called when a server is starting, and sends it to the message relay.
     */
    public static void onServerStarted(String configPath, Object logger) {
        runTaskAsync(() -> {
            try {
                // TODO: Apply cross-API event system
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }

    /**
     * Called when a server stops, and sends it to the message relay.
     */
    public static void onServerStopped() {
        runTaskAsync(() -> {
            try {
                // TODO: Apply cross-API event system
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
