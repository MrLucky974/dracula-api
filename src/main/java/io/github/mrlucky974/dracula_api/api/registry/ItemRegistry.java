package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public abstract class ItemRegistry extends BaseRegistry {
    public ItemRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends Item> T register(String name, Function<Item.Settings, T> itemFactory, Item.Settings itemSettings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, modEntrypoint.id(name));
        T item = itemFactory.apply(itemSettings.registryKey(itemKey));
        return Registry.register(Registries.ITEM, itemKey, item);
    }
}
