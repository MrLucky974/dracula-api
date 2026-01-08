package io.github.mrlucky974.dracula_api.test.item;

import io.github.mrlucky974.dracula_api.DraculaAPI;
import io.github.mrlucky974.dracula_api.api.ModReference;
import io.github.mrlucky974.dracula_api.api.ModRegistry;
import io.github.mrlucky974.dracula_api.api.item.CrossbowChargeType;
import io.github.mrlucky974.dracula_api.api.item.CrossbowChargeTypeProvider;

@ModReference(DraculaAPI.class)
public class ExampleCrossbowChargeTypes extends CrossbowChargeTypeProvider {
    public static final CrossbowChargeType TEST = CrossbowChargeTypeProvider.of("test");
}
