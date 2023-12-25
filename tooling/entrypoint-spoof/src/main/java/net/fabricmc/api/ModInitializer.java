package net.fabricmc.api;

/** Fake Fabric interface to simplify the creation of entrypoints. */
@FunctionalInterface
public interface ModInitializer {
    void onInitialize();
}
