package dev.neuralnexus.taterlib.server;

/** Abstracts a Minecraft server. */
public interface Server extends SimpleServer {
    /**
     * Get the server's TPS.
     *
     * @return The server's TPS.
     */
    //    double[] getTPS();

    /**
     * Get the server's Current TPS.
     *
     * @return The server's Current TPS.
     */
    //    default double getCurrentTPS() {
    //        return getTPS()[0];
    //    }
}
