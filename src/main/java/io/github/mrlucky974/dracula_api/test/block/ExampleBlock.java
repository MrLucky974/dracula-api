package io.github.mrlucky974.dracula_api.test.block;

import com.mojang.serialization.MapCodec;
import io.github.mrlucky974.dracula_api.test.block.entity.ExampleBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jspecify.annotations.Nullable;

public class ExampleBlock extends BlockWithEntity {
    public static final MapCodec<ExampleBlock> CODEC = FurnaceBlock.createCodec(ExampleBlock::new);

    public ExampleBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ExampleBlockEntity(pos, state);
    }
}
