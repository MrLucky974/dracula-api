package io.github.mrlucky974.dracula_api.client;

import io.github.mrlucky974.dracula_api.api.client.model.CrossbowModelPlugin;
import net.fabricmc.api.ClientModInitializer;

public class DraculaAPIClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CrossbowModelPlugin.register();
    }
}
