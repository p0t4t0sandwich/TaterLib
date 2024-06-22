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
