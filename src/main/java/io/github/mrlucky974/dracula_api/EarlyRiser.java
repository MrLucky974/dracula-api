package io.github.mrlucky974.dracula_api;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import io.github.mrlucky974.dracula_api.api.item.CrossbowChargeTypeBootstrap;
import io.github.mrlucky974.dracula_api.api.util.ModUtil;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.util.List;

public class EarlyRiser implements Runnable {

    @Override
    public void run() {
        List<ModInitializer> providers = FabricLoader.getInstance()
                .getEntrypoints("main", ModInitializer.class);

        for (ModInitializer initializer : providers) {
            if (initializer instanceof ModEntrypoint modEntrypoint) {
                ModUtil.register(modEntrypoint.getClass());
            }
        }

        CrossbowChargeTypeBootstrap.build();
    }
}
