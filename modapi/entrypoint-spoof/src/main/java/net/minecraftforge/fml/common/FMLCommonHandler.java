package net.minecraftforge.fml.common;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;

public class FMLCommonHandler {
    private static final FMLCommonHandler INSTANCE = new FMLCommonHandler();

    public static FMLCommonHandler instance() {
        return INSTANCE;
    }

    private static MinecraftServer server;
    public MinecraftServer getMinecraftServerInstance() {
        return server;
    }

    private Side side;
    public Side getSide() {
        return side;
    }
}
