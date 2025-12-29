package io.github.mrlucky974.dracula_api.api.util;

import io.github.mrlucky974.dracula_api.DraculaAPI;
import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class TranslationHelper {
    public static void addText(@NotNull FabricLanguageProvider.TranslationBuilder translationBuilder, @NotNull Text text, @NotNull String value) {
        if (text.getContent() instanceof TranslatableTextContent translatableTextContent) {
            translationBuilder.add(translatableTextContent.getKey(), value);
        } else {
            ModEntrypoint.getCommon(DraculaAPI.MOD_ID).logger()
                    .warn("Failed to add translation for text: {}", text.getString());
        }
    }

    public static String translateId(String prefix, Identifier id) {
        return prefix + id.getNamespace() + "." + id.getPath().replace('/', '.');
    }
}
