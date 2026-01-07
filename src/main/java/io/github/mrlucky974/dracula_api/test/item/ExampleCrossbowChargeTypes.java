package io.github.mrlucky974.dracula_api.test.item;

import io.github.mrlucky974.dracula_api.DraculaAPI;
import io.github.mrlucky974.dracula_api.api.Registry;
import io.github.mrlucky974.dracula_api.api.item.CrossbowChargeType;
import io.github.mrlucky974.dracula_api.api.item.CrossbowChargeTypeProvider;
import net.minecraft.util.Identifier;

@Registry(DraculaAPI.class)
public class ExampleCrossbowChargeTypes extends CrossbowChargeTypeProvider {
    public static final CrossbowChargeType TEST = CrossbowChargeTypeProvider.of("test");
}
