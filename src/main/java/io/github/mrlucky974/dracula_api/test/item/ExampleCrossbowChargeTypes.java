package io.github.mrlucky974.dracula_api.test.item;

import io.github.mrlucky974.dracula_api.DraculaAPI;
import io.github.mrlucky974.dracula_api.api.item.CrossbowChargeType;
import io.github.mrlucky974.dracula_api.api.item.CrossbowChargeTypeProvider;
import net.minecraft.util.Identifier;

public class ExampleCrossbowChargeTypes extends CrossbowChargeTypeProvider {
    public static final CrossbowChargeType TEST = CrossbowChargeTypeProvider.of(
            Identifier.of(DraculaAPI.MOD_ID, "test")
    );
}
