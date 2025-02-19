package cpw.mods.fml.common;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.server.MinecraftServer;

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
