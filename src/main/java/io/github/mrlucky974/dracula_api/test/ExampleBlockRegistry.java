package io.github.mrlucky974.dracula_api.test;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import io.github.mrlucky974.dracula_api.api.registry.BlockRegistry;
import io.github.mrlucky974.dracula_api.test.block.ExampleBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;

public class ExampleBlockRegistry extends BlockRegistry {
    public static ExampleBlock EXAMPLE_BLOCK;

    public ExampleBlockRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    @Override
    public void init() {
        EXAMPLE_BLOCK = register("example_block", ExampleBlock::new,
                AbstractBlock.Settings.copy(Blocks.COBBLESTONE), true);
    }
}
