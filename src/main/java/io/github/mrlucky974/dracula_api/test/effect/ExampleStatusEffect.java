package io.github.mrlucky974.dracula_api.test.effect;

import io.github.mrlucky974.dracula_api.api.effect.BetterStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ExampleStatusEffect extends BetterStatusEffect {
    public ExampleStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
}
