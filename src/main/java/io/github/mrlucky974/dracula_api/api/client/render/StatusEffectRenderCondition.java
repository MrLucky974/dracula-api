package io.github.mrlucky974.dracula_api.api.client.render;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

public final class StatusEffectRenderCondition implements RenderCondition {
    private final RegistryEntry<StatusEffect> statusEffect;

    private StatusEffectRenderCondition(RegistryEntry<StatusEffect> statusEffect) {
        this.statusEffect = statusEffect;
    }

    public static StatusEffectRenderCondition hasEffect(RegistryEntry<StatusEffect> statusEffect) {
        return new StatusEffectRenderCondition(statusEffect);
    }

    @Override
    public boolean shouldRender(Context context) {
        ClientPlayerEntity player = context.player();
        return statusEffect != null && player.hasStatusEffect(statusEffect);
    }
}
