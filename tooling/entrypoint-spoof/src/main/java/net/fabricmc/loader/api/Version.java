package net.fabricmc.loader.api;

/** Fake Fabric interface. */
public interface Version extends Comparable<Version> {
    String getFriendlyString();
}
