/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.conditionalmixins;

import static dev.neuralnexus.taterlib.Utils.ansiParser;

import dev.neuralnexus.conditionalmixins.annotations.ReqDependency;
import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqPlatform;
import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.PlatformData;
import dev.neuralnexus.taterlib.api.impl.metadata.PlatformDataImpl;
import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.logger.impl.SystemLogger;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.service.MixinService;

import java.io.IOException;

/**
 * Utility class for users that what to use conditional mixins allong with their own mixin plugin
 */
public class ConditionalMixins {
    private static final Logger logger = new SystemLogger("conditionalmixins");
    private static final Platform platform = Platform.get();
    private static final PlatformData platformData = new PlatformDataImpl();
    private static final MinecraftVersion minecraftVersion = platformData.minecraftVersion();

    public static boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        try {
            ClassNode classNode =
                    MixinService.getService().getBytecodeProvider().getClassNode(mixinClassName);

            if (classNode.visibleAnnotations != null) {
                for (AnnotationNode node : classNode.visibleAnnotations) {
                    if (Type.getDescriptor(ReqDependency.class).equals(node.desc)) {
                        if (!checkReqDependency(node, mixinClassName)) {
                            return false;
                        }
                    } else if (Type.getDescriptor(ReqPlatform.class).equals(node.desc)) {
                        if (!checkReqPlatform(node, mixinClassName)) {
                            return false;
                        }
                    } else if (Type.getDescriptor(ReqMCVersion.class).equals(node.desc)) {
                        if (!checkReqMCVersion(node, mixinClassName)) {
                            return false;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            logger.error("Failed to load mixin class: " + mixinClassName, e);
        }
        return true;
    }

    public static boolean checkReqDependency(AnnotationNode annotation, String mixinClassName) {
        String[] reqDependency = getAnnotationValue(annotation, "value", null);
        if (reqDependency != null) {
            for (String dep : reqDependency) {
                if (dep.startsWith("!")) {
                    String dependency = dep.substring(1);
                    if (platformData.isModLoaded(dependency)) {
                        logger.info(
                                ansiParser(
                                        "§4Skipping mixin §9"
                                                + mixinClassName
                                                + " §4conflicts with dependency: §9"
                                                + dependency));
                        return false;
                    }
                } else {
                    if (!platformData.isModLoaded(dep)) {
                        logger.info(
                                ansiParser(
                                        "§4Skipping mixin §9"
                                                + mixinClassName
                                                + " §4missing dependency: §9"
                                                + dep));
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkReqPlatform(AnnotationNode annotation, String mixinClassName) {
        Platform[] reqPlatform = getAnnotationValue(annotation, "value", null);
        if (reqPlatform != null) {
            for (Platform plat : reqPlatform) {
                if (!plat.is(platform)) {
                    logger.info(
                            ansiParser(
                                    "§4Skipping mixin §9"
                                            + mixinClassName
                                            + " §no supported platform detected"));
                    return false;
                }
            }
        }
        Platform[] reqNotPlatform = getAnnotationValue(annotation, "not", null);
        if (reqNotPlatform != null) {
            for (Platform plat : reqNotPlatform) {
                if (plat.is(platform)) {
                    logger.info(
                            ansiParser(
                                    "§4Skipping mixin §9"
                                            + mixinClassName
                                            + " §4platform not supported"));
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkReqMCVersion(AnnotationNode annotation, String mixinClassName) {
        MinecraftVersion min = getAnnotationValue(annotation, "min", null);
        MinecraftVersion max = getAnnotationValue(annotation, "max", null);
        if (min != null && !minecraftVersion.isAtLeast(min)) {
            logger.info(
                    ansiParser(
                            "§4Skipping mixin §9"
                                    + mixinClassName
                                    + " §4Minecraft version is too old"));
            return false;
        }
        if (max != null && !minecraftVersion.isAtMost(max)) {
            logger.info(
                    ansiParser(
                            "§4Skipping mixin §9"
                                    + mixinClassName
                                    + " §4Minecraft version is too recent"));
            return false;
        }

        MinecraftVersion[] versions = getAnnotationValue(annotation, "value", null);
        if (versions != null) {
            for (MinecraftVersion version : versions) {
                if (minecraftVersion.is(version)) {
                    return true;
                }
            }
            logger.info(
                    ansiParser(
                            "§4Skipping mixin §9"
                                    + mixinClassName
                                    + " §4Minecraft version not supported"));
            return false;
        }
        return true;
    }

    /**
     * Borrowed this from <a
     * href="https://github.com/Moulberry/MixinConstraints/blob/master/src/main/java/com/moulberry/mixinconstraints/checker/AnnotationChecker.java#L86">Moulberry's
     * MixinConstraints</a>
     */
    @SuppressWarnings("unchecked")
    private static <T> T getAnnotationValue(AnnotationNode annotation, String key, T defaultValue) {
        boolean getNextValue = false;
        boolean skipNextValue = false;

        if (annotation == null || annotation.values == null) {
            return defaultValue;
        }

        // Keys and value are stored in successive pairs, search for the key and if found return the
        // following entry
        for (Object value : annotation.values) {
            if (skipNextValue) {
                skipNextValue = false;
                continue;
            }
            if (getNextValue) {
                return (T) value;
            }
            if (value.equals(key)) {
                getNextValue = true;
            } else {
                skipNextValue = true;
            }
        }

        return defaultValue;
    }
}
