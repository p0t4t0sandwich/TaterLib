package dev.neuralnexus.conditionalmixins.api.bootstrap;

import dev.neuralnexus.conditionalmixins.api.ServerType;
import dev.neuralnexus.conditionalmixins.api.bootstrap.forge.CPWLoaderData;
import dev.neuralnexus.conditionalmixins.api.bootstrap.forge.FMLLoaderData;
import dev.neuralnexus.conditionalmixins.api.bootstrap.forge.MCFLoaderData;

import static dev.neuralnexus.conditionalmixins.Utils.reflectCheck;

public class InitData {
    public static BootstrapData initBootstrap() {
        ServerType serverType = ServerType.serverType();
        if (serverType.isBungeeCordBased()) {
            return new BungeeCordData();
        } else if (serverType.isFabricBased()) {
            return new FabricData();
        } else if (serverType.isNeoForgeBased()) {
            return new NeoForgeData();
        } else if (serverType.isForgeBased()) {
            if (reflectCheck("net.minecraftforge.fml.loading.FMLLoader")) {
                return new FMLLoaderData();
            } else if (reflectCheck("net.minecraftforge.fml.common.Loader")) {
                return new MCFLoaderData();
            } else if (reflectCheck("cpw.mods.fml.common.Loader")) {
                return new CPWLoaderData();
            }
        } else if (serverType.isBukkitBased()) {
            return new BukkitData();
        } else if (serverType.isSpongeBased()) {
            version = getSpongeMCVersion();
        } else if (serverType.isVelocityBased()) {
            version = getVelocityMCVersion();
        }
        return null;
    }
}
