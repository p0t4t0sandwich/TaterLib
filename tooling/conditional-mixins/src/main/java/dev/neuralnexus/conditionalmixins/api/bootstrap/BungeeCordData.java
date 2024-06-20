package dev.neuralnexus.conditionalmixins.api.bootstrap;

import net.md_5.bungee.api.ProxyServer;

public class BungeeCordData implements BootstrapData {
    @Override
    public String MinecraftVersion() {
        return ProxyServer.getInstance().getVersion();
    }
}
