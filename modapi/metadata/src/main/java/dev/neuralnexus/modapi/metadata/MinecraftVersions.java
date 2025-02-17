/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import dev.neuralnexus.modapi.metadata.impl.version.Alpha;
import dev.neuralnexus.modapi.metadata.impl.version.Beta;
import dev.neuralnexus.modapi.metadata.impl.version.Classic;
import dev.neuralnexus.modapi.metadata.impl.version.Indev;
import dev.neuralnexus.modapi.metadata.impl.version.Infdev;
import dev.neuralnexus.modapi.metadata.impl.version.MinecraftVersionImpl;
import dev.neuralnexus.modapi.metadata.impl.version.PreClassic;
import dev.neuralnexus.modapi.metadata.impl.version.Release;

import java.lang.reflect.Field;

public final class MinecraftVersions
        implements PreClassic, Classic, Indev, Infdev, Alpha, Beta, Release {
    public static final MinecraftVersion UNKNOWN = MinecraftVersionImpl.of("unknown");

    /**
     * Create a MinecraftVersion from a string.
     *
     * @param version The asString to create
     * @return The MinecraftVersion
     */
    static MinecraftVersion of(String version) {
        if (version.contains("(MC: 1.7.3)")) {
            return B1_7_3;
        }
        MinecraftVersion ver = MinecraftVersions.UNKNOWN;
        if (!version.toLowerCase().contains("a1") && !version.toLowerCase().contains("b1")) {
            for (MinecraftVersion v : Cache.reversedVersions()) {
                if (version.contains(v.toString())) {
                    ver = v;
                    break;
                }
            }
        } else {
            for (MinecraftVersion v : Cache.versions()) {
                if (version.contains(v.toString())) {
                    ver = v;
                    break;
                }
            }
        }
        return ver;
    }

    public static class Cache {
        private static final Logger logger = Logger.create("MinecraftVersionsCache");

        private static MinecraftVersion[] VERSIONS = {};
        private static MinecraftVersion[] REVERSED_VERSIONS = {};

        public static MinecraftVersion[] versions(boolean keepSnapshots, boolean force) {
            if (VERSIONS == null) {
                VERSIONS = new MinecraftVersion[0];
            }
            if (VERSIONS.length > 0 && !force) {
                return VERSIONS;
            }

            Field[] preClassicFields = PreClassic.class.getDeclaredFields();
            MinecraftVersion[] preClassicVersions = new MinecraftVersion[preClassicFields.length];
            int count = -1;
            for (Field field : preClassicFields) {
                try {
                    MinecraftVersion version = (MinecraftVersion) field.get(null);
                    count++;
                    preClassicVersions[count] = version;
                } catch (IllegalAccessException e) {
                    logger.error("Failed to get PreClassic asString", e);
                }
            }

            Field[] classicFields = Classic.class.getDeclaredFields();
            MinecraftVersion[] classicVersions = new MinecraftVersion[classicFields.length];
            count = -1;
            for (Field field : classicFields) {
                try {
                    MinecraftVersion version = (MinecraftVersion) field.get(null);
                    count++;
                    classicVersions[count] = version;
                } catch (IllegalAccessException e) {
                    logger.error("Failed to get Classic asString", e);
                }
            }

            Field[] indevFields = Indev.class.getDeclaredFields();
            MinecraftVersion[] indevVersions = new MinecraftVersion[indevFields.length];
            count = -1;
            for (Field field : indevFields) {
                try {
                    MinecraftVersion version = (MinecraftVersion) field.get(null);
                    count++;
                    indevVersions[count] = version;
                } catch (IllegalAccessException e) {
                    logger.error("Failed to get Indev asString", e);
                }
            }

            Field[] infdevFields = Infdev.class.getDeclaredFields();
            MinecraftVersion[] infdevVersions = new MinecraftVersion[infdevFields.length];
            count = -1;
            for (Field field : infdevFields) {
                try {
                    MinecraftVersion version = (MinecraftVersion) field.get(null);
                    count++;
                    infdevVersions[count] = version;
                } catch (IllegalAccessException e) {
                    logger.error("Failed to get Infdev asString", e);
                }
            }

            Field[] alphaFields = Alpha.class.getDeclaredFields();
            MinecraftVersion[] alphaVersions = new MinecraftVersion[alphaFields.length];
            count = -1;
            for (Field field : alphaFields) {
                try {
                    MinecraftVersion version = (MinecraftVersion) field.get(null);
                    count++;
                    alphaVersions[count] = version;
                } catch (IllegalAccessException e) {
                    logger.error("Failed to get Alpha asString", e);
                }
            }

            Field[] betaFields = Beta.class.getDeclaredFields();
            MinecraftVersion[] betaVersions = new MinecraftVersion[betaFields.length];
            count = -1;
            for (Field field : betaFields) {
                try {
                    MinecraftVersion version = (MinecraftVersion) field.get(null);
                    count++;
                    betaVersions[count] = version;
                } catch (IllegalAccessException e) {
                    logger.error("Failed to get Beta asString", e);
                }
            }

            Field[] releaseFields = Release.class.getDeclaredFields();
            MinecraftVersion[] releaseVersions = new MinecraftVersion[releaseFields.length];
            count = -1;
            for (Field field : releaseFields) {
                try {
                    MinecraftVersion version = (MinecraftVersion) field.get(null);
                    if (!keepSnapshots && version.meta().type() != MinecraftVersion.Type.RELEASE) {
                        continue;
                    }
                    count++;
                    releaseVersions[count] = version;
                } catch (IllegalAccessException e) {
                    logger.error("Failed to get Release asString", e);
                }
            }

            int preLength = preClassicVersions.length;
            int classicLength = classicVersions.length;
            int indevLength = indevVersions.length;
            int infdevLength = infdevVersions.length;
            int alphaLength = alphaVersions.length;
            int betaLength = betaVersions.length;
            int releaseLength = count + 1;
            int totalLength =
                    preLength
                            + classicLength
                            + indevLength
                            + infdevLength
                            + alphaLength
                            + betaLength
                            + releaseLength;

            VERSIONS = new MinecraftVersion[totalLength];
            int index = 0;
            System.arraycopy(preClassicVersions, 0, VERSIONS, index, preLength);
            index += preLength;
            System.arraycopy(classicVersions, 0, VERSIONS, index, classicLength);
            index += classicLength;
            System.arraycopy(indevVersions, 0, VERSIONS, index, indevLength);
            index += indevLength;
            System.arraycopy(infdevVersions, 0, VERSIONS, index, infdevLength);
            index += infdevLength;
            System.arraycopy(alphaVersions, 0, VERSIONS, index, alphaLength);
            index += alphaLength;
            System.arraycopy(betaVersions, 0, VERSIONS, index, betaLength);
            index += betaLength;
            System.arraycopy(releaseVersions, 0, VERSIONS, index, releaseLength);

            return VERSIONS;
        }

        public static MinecraftVersion[] versions() {
            return versions(false, false);
        }

        public static MinecraftVersion[] reversedVersions() {
            if (REVERSED_VERSIONS == null) {
                REVERSED_VERSIONS = new MinecraftVersion[0];
            }
            if (REVERSED_VERSIONS.length > 0) {
                return REVERSED_VERSIONS;
            }

            if (VERSIONS == null || VERSIONS.length == 0) {
                versions();
            }
            REVERSED_VERSIONS = new MinecraftVersion[VERSIONS.length];
            int index = 0;
            for (int i = VERSIONS.length - 1; i >= 0; i--) {
                REVERSED_VERSIONS[index] = VERSIONS[i];
                index++;
            }

            return REVERSED_VERSIONS;
        }
    }
}
