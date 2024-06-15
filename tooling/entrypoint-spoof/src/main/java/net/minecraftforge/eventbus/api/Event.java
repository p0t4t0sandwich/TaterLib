package net.minecraftforge.eventbus.api;

/** Fake Forge event. */
public class Event {
    public boolean isCanceled() {
        return false;
    }

    public void setCanceled(boolean cancel) {}
}
