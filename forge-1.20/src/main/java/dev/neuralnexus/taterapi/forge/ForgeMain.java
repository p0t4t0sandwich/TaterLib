package dev.neuralnexus.taterapi.forge;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.forge.commands.ForgeTaterAPICommand;
import dev.neuralnexus.taterapi.forge.listeners.ForgePlayerLoginListener;
import dev.neuralnexus.taterapi.forge.listeners.server.ForgeServerStartingListener;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ForgeMain.MODID)
public class ForgeMain {
    public static TaterAPI taterApi;
    public static final String MODID = "taterapi";
    public static final Logger logger = LogUtils.getLogger();

    // Get server type
    public String getServerType() {
        return "Forge";
    }

    public ForgeMain() {
        logger.info("[TaterAPI]: TaterAPI is running on " + getServerType() + ".");

        // Start TaterAPI
        MinecraftForge.EVENT_BUS.register(new ForgeServerStartingListener());

        // Register player event listeners
        MinecraftForge.EVENT_BUS.register(new ForgePlayerLoginListener());

        // Register commands
        MinecraftForge.EVENT_BUS.register(ForgeTaterAPICommand.class);

        // Mod enable message
        logger.info("[TaterAPI]: TaterAPI has been enabled!");
    }
}
