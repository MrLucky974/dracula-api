package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.util.RegistryHelper;
import net.minecraft.block.Block;

import java.util.function.Function;

public abstract class BlockRegistry extends BaseRegistry {
    protected static <T extends Block> T register(String name, Function<Block.Settings, T> blockFactory, Block.Settings blockSettings, boolean shouldRegisterItem) {
        return RegistryHelper.registerBlock(id(name), blockFactory, blockSettings, shouldRegisterItem);
    }
}
