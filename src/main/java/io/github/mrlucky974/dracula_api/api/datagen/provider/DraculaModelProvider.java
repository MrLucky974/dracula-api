package io.github.mrlucky974.dracula_api.api.datagen.provider;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.ModelIds;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.jspecify.annotations.NonNull;

public abstract class DraculaModelProvider extends FabricModelProvider {
    public DraculaModelProvider(FabricDataOutput output) {
        super(output);
    }

    public final void registerPotionType(@NonNull ItemModelGenerator itemModelGenerator, Item item) {
        Identifier identifier = itemModelGenerator.uploadTwoLayers(item, ModelIds.getItemSubModelId(item, "_overlay"), ModelIds.getItemModelId(item));
        itemModelGenerator.registerPotionTinted(item, identifier);
    }
}
