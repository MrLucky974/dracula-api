package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public abstract class BlockRegistry extends BaseRegistry {
    public BlockRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends Block> T register(String name, Function<Block.Settings, T> blockFactory, Block.Settings blockSettings, boolean shouldRegisterItem) {
        return RegistryHelper.registerBlock(modEntrypoint.id(name), blockFactory, blockSettings, shouldRegisterItem);
    }
}
