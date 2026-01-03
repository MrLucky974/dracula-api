package io.github.mrlucky974.dracula_api.api.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleEffect;
import org.jspecify.annotations.Nullable;

public abstract class BetterStatusEffect extends StatusEffect
        implements EffectApplyCallback, EffectRemoveCallback {
    public BetterStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public BetterStatusEffect(StatusEffectCategory category, int color, ParticleEffect particleEffect) {
        super(category, color, particleEffect);
    }

    @Override
    public void onApplied(LivingEntity entity, @Nullable Entity source, int amplifier) {

    }

    @Override
    public void onRemoved(LivingEntity livingEntity) {

    }
}
