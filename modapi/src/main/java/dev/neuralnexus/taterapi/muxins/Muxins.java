/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.muxins;

import static dev.neuralnexus.taterapi.meta.impl.util.TextUtil.ansiParser;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.logger.Logger;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.service.MixinService;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * Utility class for users that what to use conditional mixins allong with their own mixin plugin
 */
public final class Muxins {
    public static final Logger logger = Logger.create("muxins");

    private Muxins() {}

    /**
     * Checks if a mixin should be applied based on its annotations
     *
     * @param mixinClassName The name of the mixin class
     * @param disabledMixins A list of disabled mixins
     * @param verbose If the method should log the result
     * @return If the mixin should be applied
     */
    public static boolean shouldApplyMixin(
            String mixinClassName, Collection<String> disabledMixins, boolean verbose) {
        boolean result = true;
        for (String disabledMixin : disabledMixins) {
            if (mixinClassName.endsWith(disabledMixin)) {
                if (verbose) {
                    logger.info(
                            ansiParser(
                                    "§4Skipping mixin §9"
                                            + mixinClassName
                                            + " §4disabled in config"));
                }
                result = false;
            }
        }
        try {
            ClassNode classNode =
                    MixinService.getService().getBytecodeProvider().getClassNode(mixinClassName);

            if (classNode.visibleAnnotations != null) {
                result =
                        AnnotationChecker.checkAnnotations(
                                classNode.visibleAnnotations, mixinClassName, verbose);
            }
        } catch (ClassNotFoundException | IOException e) {
            if (verbose) {
                logger.error("Failed to load mixin class: " + mixinClassName, e);
            }
        }
        if (result && verbose) {
            logger.info(ansiParser("§2Applying mixin §9" + mixinClassName));
        }
        return result;
    }

    /**
     * Checks if a mixin should be applied based on its annotations
     *
     * @param mixinClassName The name of the mixin class
     * @return If the mixin should be applied
     */
    public static boolean shouldApplyMixin(String mixinClassName) {
        return shouldApplyMixin(mixinClassName, Collections.emptyList(), false);
    }

    /**
     * Checks if a mixin should be applied based on its annotations
     *
     * @param mixinClassName The name of the mixin class
     * @param verbose If the method should log the result
     * @return If the mixin should be applied
     */
    public static boolean shouldApplyMixin(String mixinClassName, boolean verbose) {
        return shouldApplyMixin(mixinClassName, Collections.emptyList(), verbose);
    }

    /**
     * Checks if a mixin should be applied based on its annotations
     *
     * @param mixinClassName The name of the mixin class
     * @param disabledMixins A list of disabled mixins
     * @return If the mixin should be applied
     */
    public static boolean shouldApplyMixin(
            String mixinClassName, Collection<String> disabledMixins) {
        return shouldApplyMixin(mixinClassName, disabledMixins, false);
    }
}
