package io.github.mrlucky974.dracula_api.test;

import io.github.mrlucky974.dracula_api.DraculaAPI;
import io.github.mrlucky974.dracula_api.api.Registry;
import io.github.mrlucky974.dracula_api.api.registry.BlockRegistry;
import io.github.mrlucky974.dracula_api.test.block.ExampleBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;

@Registry(DraculaAPI.class)
public class ExampleBlockRegistry extends BlockRegistry {
    public static ExampleBlock EXAMPLE_BLOCK = register("example_block", ExampleBlock::new,
            AbstractBlock.Settings.copy(Blocks.COBBLESTONE), true);
}
