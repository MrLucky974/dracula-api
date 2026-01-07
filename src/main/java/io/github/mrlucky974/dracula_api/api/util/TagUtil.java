package io.github.mrlucky974.dracula_api.api.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class TagUtil {
    private TagUtil() {}

    private static <T> TagKey<T> tagKey(Identifier identifier, RegistryKey<? extends Registry<T>> registry) {
        return TagKey.of(registry, identifier);
    }

    public static class Items {
        public static TagKey<Item> get(Identifier identifier) {
            return tagKey(identifier, RegistryKeys.ITEM);
        };
    }

    public static class Blocks {
        public static TagKey<Block> get(Identifier identifier) {
            return tagKey(identifier, RegistryKeys.BLOCK);
        };
    }
}
