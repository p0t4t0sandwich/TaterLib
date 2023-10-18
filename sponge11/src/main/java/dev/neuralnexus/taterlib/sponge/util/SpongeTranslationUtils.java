package dev.neuralnexus.taterlib.sponge.util;

import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.translation.GlobalTranslator;
import org.spongepowered.api.Sponge;

import java.text.MessageFormat;

public class SpongeTranslationUtils {
    public static String translate(TranslatableComponent translatableComponent) {
        MessageFormat messageFormat = GlobalTranslator.translator().translate(translatableComponent.key(), Sponge.server().locale());
        Object[] args = translatableComponent.args().toArray();
        if (messageFormat == null) {
            return translatableComponent.key();
        }
        return messageFormat.format(args);
    }
}
