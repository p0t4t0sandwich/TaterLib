package net.neoforged.api.distmarker;

/**
 * NeoForge's "Side" enum
 */
public enum Dist {
    CLIENT,
    DEDICATED_SERVER;

    private Dist() {
    }

    public boolean isDedicatedServer() {
        return !this.isClient();
    }

    public boolean isClient() {
        return this == CLIENT;
    }
}
