/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.conditionalmixins;

import static dev.neuralnexus.taterapi.util.TextUtil.ansiParser;

import dev.neuralnexus.taterapi.logger.Logger;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.service.MixinService;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * Utility class for users that what to use conditional mixins allong with their own mixin plugin
 */
public class ConditionalMixins {
    public static final Logger logger = Logger.create("conditionalmixins");

    public static boolean shouldApplyMixin(String mixinClassName, Collection<String> disabledMixins, boolean verbose) {
        for (String disabledMixin : disabledMixins) {
            if (mixinClassName.endsWith(disabledMixin)) {
                if (verbose) {
                    logger.info(ansiParser("§4Skipping mixin §9" + mixinClassName + " §4disabled in config"));
                }
                return false;
            }
        }
        try {
            ClassNode classNode =
                    MixinService.getService().getBytecodeProvider().getClassNode(mixinClassName);

            if (classNode.visibleAnnotations != null) {
                return AnnotationChecker.checkAnnotations(classNode.visibleAnnotations, mixinClassName, verbose);
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

    public static boolean shouldApplyMixin(String mixinClassName) {
        return shouldApplyMixin(mixinClassName, Collections.emptyList(), false);
    }

    public static boolean shouldApplyMixin(String mixinClassName, boolean verbose) {
        return shouldApplyMixin(mixinClassName, Collections.emptyList(), verbose);
    }

    public static boolean shouldApplyMixin(String mixinClassName, Collection<String> disabledMixins) {
        return shouldApplyMixin(mixinClassName, disabledMixins, false);
    }
}
