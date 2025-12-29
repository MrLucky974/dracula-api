package io.github.mrlucky974.dracula_api.test.block.entity;

import io.github.mrlucky974.dracula_api.test.ExampleBlockEntityTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class ExampleBlockEntity extends BlockEntity {
    public ExampleBlockEntity(BlockPos pos, BlockState state) {
        super(ExampleBlockEntityTypeRegistry.EXAMPLE_BLOCK_ENTITY, pos, state);
    }
}
