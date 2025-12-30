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
        return RegistryHelper.registerItem(modEntrypoint.id(name), itemFactory, itemSettings);
    }
}
