package dev.neuralnexus.taterlib.server;

/** Abstracts a Minecraft server. */
public interface Server extends SimpleServer {
    /**
     * Get the server's TPS.
     *
     * @return The server's TPS.
     */
    //    double[] tps();

    /**
     * Get the server's Current TPS.
     *
     * @return The server's Current TPS.
     */
    //    default double currentTPS() {
    //        return tps()[0];
    //    }
}
