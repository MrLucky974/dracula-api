package io.github.mrlucky974.dracula_api.api.util;

import io.github.mrlucky974.dracula_api.DraculaAPI;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.jetbrains.annotations.NotNull;

public class TranslationHelper {
    public static void addText(@NotNull FabricLanguageProvider.TranslationBuilder translationBuilder, @NotNull Text text, @NotNull String value) {
        if (text.getContent() instanceof TranslatableTextContent translatableTextContent) {
            translationBuilder.add(translatableTextContent.getKey(), value);
        } else {
            DraculaAPI.LOGGER.warn("Failed to add translation for text: {}", text.getString());
        }
    }

    protected static <T extends Translatable> void addTranslatable(@NotNull FabricLanguageProvider.TranslationBuilder translationBuilder,
                                   @NotNull T translatable, @NotNull String value) {
        translationBuilder.add(translatable.getTranslationKey(), value);
    }
}
