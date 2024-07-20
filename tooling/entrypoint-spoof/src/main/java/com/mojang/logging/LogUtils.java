/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package com.mojang.logging;

import org.slf4j.Logger;

/** Fake Mojang Logger class to simplify the creation of entrypoints. */
public class LogUtils {
    public static Logger getLogger() {
        return null;
    }
}
