package io.github.mrlucky974.dracula_api.api.item;

import net.minecraft.util.Identifier;

public abstract class CrossbowChargeTypeProvider {
    protected static CrossbowChargeType of(Identifier identifier) {
        return CrossbowChargeTypeInternals.register(identifier);
    }
}