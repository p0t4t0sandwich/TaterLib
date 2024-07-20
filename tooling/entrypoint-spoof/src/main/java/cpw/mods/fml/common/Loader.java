/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package cpw.mods.fml.common;

import java.util.Collections;
import java.util.List;

/** Fake Forge class. */
public class Loader {
    public static final String MC_VERSION = "0";
    private static Loader instance;

    public static Loader instance() {
        if (instance == null) {
            instance = new Loader();
        }
        return instance;
    }

    public List<ModContainer> getModList() {
        return Collections.emptyList();
    }
}
