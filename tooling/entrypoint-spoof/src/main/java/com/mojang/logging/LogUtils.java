package com.mojang.logging;

import org.slf4j.Logger;

/** Fake Mojang Logger class to simplify the creation of entrypoints. */
public class LogUtils {
    public static Logger getLogger() {
        return null;
    }
}
