package io.github.mrlucky974.dracula_api.api.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class RegistryHelper {
    public static <T extends Block> T registerBlock(Identifier identifier, Function<Block.Settings, T> blockFactory, Block.Settings blockSettings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        T block = blockFactory.apply(blockSettings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, identifier);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    public static <T extends ComponentType<?>> T registerComponent(Identifier identifier, T component) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, identifier, component);
    }

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntityType(Identifier identifier, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory, Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, identifier, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static <T extends Criterion<?>> T registerCriterion(Identifier identifier, T criterion) {
        return Criteria.register(identifier.getNamespace() + "/" + identifier.getPath(), criterion);
    }

    public static <T extends ItemGroup> T registerItemGroup(Identifier identifier, T itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, identifier, itemGroup);
    }

    public static <T extends Item> T registerItem(Identifier identifier, Function<Item.Settings, T> itemFactory, Item.Settings itemSettings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, identifier);
        T item = itemFactory.apply(itemSettings.registryKey(itemKey));
        return Registry.register(Registries.ITEM, itemKey, item);
    }

    public static <T extends Potion> T registerPotion(Identifier identifier, T potion) {
        return Registry.register(Registries.POTION, identifier, potion);
    }

    public static <T extends RecipeType<?>> T registerRecipeType(Identifier identifier, T type) {
        return Registry.register(Registries.RECIPE_TYPE, identifier, type);
    }

    public static <T extends RecipeSerializer<?>> T registerRecipeSerializer(Identifier identifier, T serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, identifier, serializer);
    }

    public static <T extends ScreenHandler> ScreenHandlerType<T> registerScreenHandler(Identifier identifier, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, identifier, new ScreenHandlerType<>(factory, FeatureSet.empty()));
    }

    public static <T extends ScreenHandler, D extends CustomPayload> ExtendedScreenHandlerType<T, D>
    registerScreenHandler(Identifier identifier,
             ExtendedScreenHandlerType.ExtendedFactory<T, D> factory,
             PacketCodec<? super RegistryByteBuf, D> codec) {
        return Registry.register(Registries.SCREEN_HANDLER, identifier, new ExtendedScreenHandlerType<>(factory, codec));
    }

    public static <T extends StatusEffect> RegistryEntry<T> registerStatusEffect(Identifier identifier, T statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, identifier, statusEffect);
    }
}
