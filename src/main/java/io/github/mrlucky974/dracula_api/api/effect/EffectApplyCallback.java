package io.github.mrlucky974.dracula_api.api.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jspecify.annotations.Nullable;

@FunctionalInterface
public interface EffectApplyCallback {
    void onApplied(LivingEntity entity, @Nullable Entity source, int amplifier);
}
