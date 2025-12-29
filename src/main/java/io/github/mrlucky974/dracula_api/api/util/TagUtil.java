package io.github.mrlucky974.dracula_api.api.util;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public final class TagUtil {
    private TagUtil() {}

    private static <T> TagKey<T> tagKey(ModEntrypoint modEntrypoint, String name, RegistryKey<? extends Registry<T>> registry) {
        return TagKey.of(registry, modEntrypoint.id(name));
    }

    public static class Items {
        public static TagKey<Item> get(String modId, String name) {
            return get(ModEntrypoint.get(modId), name);
        };

        public static TagKey<Item> get(ModEntrypoint modEntrypoint, String name) {
            return tagKey(modEntrypoint, name, RegistryKeys.ITEM);
        };
    }

    public static class Blocks {
        public static TagKey<Block> get(String modId, String name) {
            return get(ModEntrypoint.get(modId), name);
        };

        public static TagKey<Block> get(ModEntrypoint modEntrypoint, String name) {
            return tagKey(modEntrypoint, name, RegistryKeys.BLOCK);
        };
    }
}
