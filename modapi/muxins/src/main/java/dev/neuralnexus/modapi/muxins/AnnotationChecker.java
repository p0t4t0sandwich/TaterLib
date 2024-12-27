/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.muxins;

import static dev.neuralnexus.modapi.metadata.impl.util.TextUtil.ansiParser;
import static dev.neuralnexus.modapi.muxins.Muxins.logger;

import static org.spongepowered.asm.util.Annotations.getValue;

import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.enums.Platform;
import dev.neuralnexus.modapi.muxins.annotations.ReqDependency;
import dev.neuralnexus.modapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.modapi.muxins.annotations.ReqPlatform;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;

import java.util.List;

public class AnnotationChecker {
    private static final dev.neuralnexus.modapi.metadata.Platform.Meta meta =
            MetaAPI.instance().meta();
    private static final MinecraftVersion minecraftVersion = MetaAPI.instance().version();

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
                    if (meta.isModLoaded(dependency)) {
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
                    if (!meta.isModLoaded(dep)) {
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
        if (mappings != Mappings.NONE && !mappings.is(meta.mappings())) {
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
                if (!MetaAPI.instance().isPlatformPresent(plat.ref())) {
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
                if (MetaAPI.instance().isPlatformPresent(plat.ref())) {
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
        MinecraftVersion min = getValue(annotation, "min", MinecraftVersions.UNKNOWN);
        MinecraftVersion max = getValue(annotation, "max", MinecraftVersions.UNKNOWN);
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

        List<MinecraftVersion> versions = getValue(annotation, "value", true);
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