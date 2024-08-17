/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.conditionalmixins;

import static dev.neuralnexus.conditionalmixins.ConditionalMixins.logger;
import static dev.neuralnexus.taterapi.util.TextUtil.ansiParser;

import static org.spongepowered.asm.util.Annotations.getValue;

import dev.neuralnexus.conditionalmixins.annotations.ReqDependency;
import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqMappings;
import dev.neuralnexus.conditionalmixins.annotations.ReqPlatform;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.metadata.PlatformData;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;

import java.util.List;

public class AnnotationChecker {
    private static final Platform platform = Platform.get();
    private static final PlatformData platformData = PlatformData.instance();
    private static final MinecraftVersion minecraftVersion = platformData.minecraftVersion();

    public static boolean checkAnnotations(
            List<AnnotationNode> annotations, String mixinClassName, boolean verbose) {
        for (AnnotationNode node : annotations) {
            if (Type.getDescriptor(ReqDependency.class).equals(node.desc)) {
                if (!checkReqDependency(mixinClassName, node, verbose)) {
                    return false;
                }
            } else if (Type.getDescriptor(ReqMappings.class).equals(node.desc)) {
                if (!checkReqMappings(mixinClassName, node, verbose)) {
                    return false;
                }
            } else if (Type.getDescriptor(ReqPlatform.class).equals(node.desc)) {
                if (!checkReqPlatform(mixinClassName, node, verbose)) {
                    return false;
                }
            } else if (Type.getDescriptor(ReqMCVersion.class).equals(node.desc)) {
                if (!checkReqMCVersion(mixinClassName, node, verbose)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkReqDependency(
            String mixinClassName, AnnotationNode annotation, boolean verbose) {
        List<String> reqDependency = getValue(annotation, "value", true);
        if (!reqDependency.isEmpty()) {
            for (String dep : reqDependency) {
                if (dep.startsWith("!")) {
                    String dependency = dep.substring(1);
                    if (platformData.isModLoaded(dependency)) {
                        if (verbose) {
                            logger.info(
                                    ansiParser(
                                            "§4Skipping mixin §9"
                                                    + mixinClassName
                                                    + " §4conflicts with dependency: §9"
                                                    + dependency));
                        }
                        return false;
                    }
                } else {
                    if (!platformData.isModLoaded(dep)) {
                        if (verbose) {
                            logger.info(
                                    ansiParser(
                                            "§4Skipping mixin §9"
                                                    + mixinClassName
                                                    + " §4missing dependency: §9"
                                                    + dep));
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkReqMappings(
            String mixinClassName, AnnotationNode annotation, boolean verbose) {
        Mappings mappings = getValue(annotation, "value", Mappings.class, Mappings.NONE);
        if (mappings != Mappings.NONE && !mappings.is(platformData.mappings())) {
            if (verbose) {
                logger.info(
                        ansiParser(
                                "§4Skipping mixin §9"
                                        + mixinClassName
                                        + " §4mappings not supported"));
            }
            return false;
        }
        return true;
    }

    public static boolean checkReqPlatform(
            String mixinClassName, AnnotationNode annotation, boolean verbose) {
        List<Platform> platforms = getValue(annotation, "value", true, Platform.class);
        if (!platforms.isEmpty()) {
            for (Platform plat : platforms) {
                if (!plat.is(platform)) {
                    if (verbose) {
                        logger.info(
                                ansiParser(
                                        "§4Skipping mixin §9"
                                                + mixinClassName
                                                + " §4no supported platform detected"));
                    }
                    return false;
                }
            }
        }
        List<Platform> notPlatforms = getValue(annotation, "not", true, Platform.class);
        if (!notPlatforms.isEmpty()) {
            for (Platform plat : notPlatforms) {
                if (plat.is(platform)) {
                    if (verbose) {
                        logger.info(
                                ansiParser(
                                        "§4Skipping mixin §9"
                                                + mixinClassName
                                                + " §4platform not supported"));
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkReqMCVersion(
            String mixinClassName, AnnotationNode annotation, boolean verbose) {
        MinecraftVersion min =
                getValue(annotation, "min", MinecraftVersion.class, MinecraftVersion.UNKNOWN);
        MinecraftVersion max =
                getValue(annotation, "max", MinecraftVersion.class, MinecraftVersion.UNKNOWN);
        if (min != null && !minecraftVersion.isAtLeast(min)) {
            if (verbose) {
                logger.info(
                        ansiParser(
                                "§4Skipping mixin §9"
                                        + mixinClassName
                                        + " §4Minecraft version is too old"));
            }
            return false;
        }
        if (max != null && !minecraftVersion.isAtMost(max)) {
            if (verbose) {
                logger.info(
                        ansiParser(
                                "§4Skipping mixin §9"
                                        + mixinClassName
                                        + " §4Minecraft version is too recent"));
            }
            return false;
        }

        List<MinecraftVersion> versions =
                getValue(annotation, "value", true, MinecraftVersion.class);
        if (!versions.isEmpty()) {
            for (MinecraftVersion version : versions) {
                if (minecraftVersion.is(version)) {
                    return true;
                }
            }
            if (verbose) {
                logger.info(
                        ansiParser(
                                "§4Skipping mixin §9"
                                        + mixinClassName
                                        + " §4Minecraft version not supported"));
            }
            return false;
        }
        return true;
    }
}
