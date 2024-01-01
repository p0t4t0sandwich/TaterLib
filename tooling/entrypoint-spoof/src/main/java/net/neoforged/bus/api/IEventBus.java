package net.neoforged.bus.api;

/** Fake event buss to allow registering NeoForge events.. */
public interface IEventBus {
    void register(Object target);
}
