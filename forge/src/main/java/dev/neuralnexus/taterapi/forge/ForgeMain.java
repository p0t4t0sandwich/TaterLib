package dev.neuralnexus.taterapi.forge;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.forge.commands.ForgeTemplateCommand;
import dev.neuralnexus.taterapi.forge.listeners.ForgePlayerLoginListener;
import dev.neuralnexus.taterapi.forge.listeners.ForgeServerStartedListener;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ForgeMain.MODID)
public class ForgeMain {
    public TaterAPI taterApi;
    public static final String MODID = "template";
    public static final Logger logger = LogUtils.getLogger();

    // Get server type
    public String getServerType() {
        return "Forge";
    }

    // Singleton instance
    private static ForgeMain instance;

    public static ForgeMain getInstance() {
        return instance;
    }


    public ForgeMain() {
        // Singleton instance
        instance = this;

        logger.info("[Template]: Template is running on " + getServerType() + ".");

        // Register event listeners
        MinecraftForge.EVENT_BUS.register(new ForgeServerStartedListener());
        MinecraftForge.EVENT_BUS.register(new ForgePlayerLoginListener());

        // Register commands
        MinecraftForge.EVENT_BUS.register(ForgeTemplateCommand.class);

        // Mod enable message
        logger.info("[Template]: Template has been enabled!");
    }
}
