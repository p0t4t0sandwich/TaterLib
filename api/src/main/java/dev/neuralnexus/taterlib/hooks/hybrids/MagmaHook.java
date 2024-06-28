package dev.neuralnexus.taterlib.hooks.hybrids;

import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.hooks.Hook;

import org.magmafoundation.magma.api.ServerAPI;

import java.util.Set;

/**
 * A hook for Mamga
 *
 * @see <a href="https://github.com/magmamaintained">Magma</a>
 */
public class MagmaHook implements Hook {
    private static MagmaHook instance;

    /** Create a new hook */
    public MagmaHook() {
        instance = this;
    }

    /**
     * Get the instance
     *
     * @return The instance
     */
    public static MagmaHook get() {
        return instance;
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return "magma";
    }

    /**
     * Get if a mod is loaded <br>
     * <b>NOTE: You can just use {@link TaterAPI#isModLoaded(String)}</b>
     *
     * @param modid The modid
     * @return If the mod is loaded
     */
    public boolean hasMod(String modid) {
        return ServerAPI.hasMod(modid);
    }

    /**
     * Get mod list
     *
     * @return The mod list
     */
    public Set<String> modList() {
        return ServerAPI.modList;
    }

    /**
     * Notes
     *
     * <p># Magma API -
     * [1.12](https://git.magmafoundation.org/magmafoundation/Magma/-/blob/master/src/main/java/org/magmafoundation/magma/api/ServerAPI.java)
     * - Has a nifty OreDict thing 1.16+ have `entityTypeMap` -
     * [1.16](https://git.magmafoundation.org/magmafoundation/Magma-1-16-x/-/blob/1.16.x/src/main/java/org/magmafoundation/magma/api/ServerAPI.java)
     * -
     * [1.18](https://git.magmafoundation.org/magmafoundation/Magma-1-18-x/-/blob/1.18.x/src/main/java/org/magmafoundation/magma/api/ServerAPI.java)
     * -
     * [1.19](https://git.magmafoundation.org/magmafoundation/Magma-1-19-x/-/blob/master/src/main/java/org/magmafoundation/magma/api/ServerAPI.java)
     * -
     * [1.20](https://git.magmafoundation.org/magmafoundation/magma-1-20-x/-/blob/1.20/src/main/java/org/magmafoundation/magma/api/ServerAPI.java)
     */
}
