/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.conditionalmixins;

import static dev.neuralnexus.taterlib.Utils.ansiParser;

import static org.spongepowered.asm.util.Annotations.getValue;

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
import java.util.List;

/**
 * Utility class for users that what to use conditional mixins allong with their own mixin plugin
 */
public class ConditionalMixins {
    private static final Logger logger = new SystemLogger("conditionalmixins");
    private static final Platform platform = Platform.get();
    private static final PlatformData platformData = new PlatformDataImpl();
    private static final MinecraftVersion minecraftVersion = platformData.minecraftVersion();

    public static boolean shouldApplyMixin(String mixinClassName) {
        return shouldApplyMixin(mixinClassName, false);
    }

    public static boolean shouldApplyMixin(String mixinClassName, boolean verbose) {
        try {
            ClassNode classNode =
                    MixinService.getService().getBytecodeProvider().getClassNode(mixinClassName);

            if (classNode.visibleAnnotations != null) {
                for (AnnotationNode node : classNode.visibleAnnotations) {
                    if (Type.getDescriptor(ReqDependency.class).equals(node.desc)) {
                        if (!checkReqDependency(mixinClassName, node, verbose)) {
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
            }
        } catch (ClassNotFoundException | IOException e) {
            if (verbose) {
                logger.error("Failed to load mixin class: " + mixinClassName, e);
            }
        }
        if (verbose) {
            logger.info(ansiParser("§2Applying mixin §9" + mixinClassName));
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

    public static boolean checkReqPlatform(
            String mixinClassName, AnnotationNode annotation, boolean verbose) {
        List<Platform> platforms =
                getValue(annotation, "value", true, Platform.class);
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
        List<Platform> notPlatforms =
                getValue(annotation, "not", true, Platform.class);
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
                getValue(
                        annotation, "min", MinecraftVersion.class, MinecraftVersion.UNKNOWN);
        MinecraftVersion max =
                getValue(
                        annotation, "max", MinecraftVersion.class, MinecraftVersion.UNKNOWN);
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
