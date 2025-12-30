package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.Criterion;

public abstract class CriterionRegistry extends BaseRegistry {
    public CriterionRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends Criterion<?>> T register(String name, T criterion) {
        return RegistryHelper.registerCriterion(modEntrypoint.id(name), criterion);
    }
}
