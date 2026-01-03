package io.github.mrlucky974.dracula_api.mixin;

import io.github.mrlucky974.dracula_api.api.effect.EffectApplyCallback;
import io.github.mrlucky974.dracula_api.api.effect.EffectRemoveCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
            method = "onStatusEffectsRemoved",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/effect/StatusEffect;onRemoved(Lnet/minecraft/entity/attribute/AttributeContainer;)V"
            )
    )
    private void draculaAPI$onStatusEffectRemoved(Collection<StatusEffectInstance> effects, CallbackInfo ci) {
        for (StatusEffectInstance effect : effects) {
            if (effect.getEffectType().value() instanceof EffectRemoveCallback parnse) {
                parnse.onRemoved((LivingEntity) (Object) this);
            }
        }
    }

    @Inject(method = "onStatusEffectApplied", at = @At("TAIL"))
    private void draculaAPI$onStatusEffectApplied(StatusEffectInstance effect, Entity source, CallbackInfo ci) {
        if (!getEntityWorld().isClient()) {
            if (effect.getEffectType().value() instanceof EffectApplyCallback parnse) {
                parnse.onApplied((LivingEntity) (Object) this, source, effect.getAmplifier());
            }
        }
    }
}
