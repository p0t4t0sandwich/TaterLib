package dev.neuralnexus.modapi.crossperms;

import dev.neuralnexus.modapi.reflecto.ClassEntry;
import dev.neuralnexus.modapi.reflecto.Reflecto;

public class Setup {
    public void setup() {
        ClassEntry entity = ClassEntry.builder()
                .name("Entity")
                .mojmap("")
                .searge("")
                .legacySearge("")
                .yarnIntermediary("net.minecraft.class_1297")
                .build();

        ClassEntry sharedSuggestionProvider = ClassEntry.builder()
                .name("SharedSuggestionProvider")
                .mojmap("")
                .searge("")
                .legacySearge("")
                .yarnIntermediary("net.minecraft.class_2172")
                .build();

        Reflecto.instance()
                .register(this, entity)
                .register(this, sharedSuggestionProvider);
    }
}
