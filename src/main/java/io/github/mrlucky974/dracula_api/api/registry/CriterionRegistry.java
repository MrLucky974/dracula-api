package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.util.RegistryHelper;
import net.minecraft.advancement.criterion.Criterion;

public abstract class CriterionRegistry extends BaseRegistry {
    protected static <T extends Criterion<?>> T register(String name, T criterion) {
        return RegistryHelper.registerCriterion(id(name), criterion);
    }
}
