package io.github.mrlucky974.dracula_api.test;

import io.github.mrlucky974.dracula_api.DraculaAPI;
import io.github.mrlucky974.dracula_api.api.Registry;
import io.github.mrlucky974.dracula_api.api.registry.BlockEntityTypeRegistry;
import io.github.mrlucky974.dracula_api.test.block.entity.ExampleBlockEntity;
import net.minecraft.block.entity.BlockEntityType;

@Registry(DraculaAPI.class)
public class ExampleBlockEntityTypeRegistry extends BlockEntityTypeRegistry {
    public static BlockEntityType<ExampleBlockEntity> EXAMPLE_BLOCK_ENTITY = register("example_block",
            ExampleBlockEntity::new,
            ExampleBlockRegistry.EXAMPLE_BLOCK);
}
