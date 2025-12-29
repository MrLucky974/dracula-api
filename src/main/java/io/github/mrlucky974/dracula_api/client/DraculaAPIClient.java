package io.github.mrlucky974.dracula_api.client;

import io.github.mrlucky974.dracula_api.DraculaAPI;
import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import io.github.mrlucky974.dracula_api.api.client.ModClientEntrypoint;
import org.slf4j.Logger;

public class DraculaAPIClient extends ModClientEntrypoint {
    public DraculaAPIClient() {
        super(DraculaAPI.MOD_ID);
    }

    @Override
    public void init() {

    }

    @Override
    public Logger logger() {
        return ModEntrypoint.get(modId).logger();
    }
}
