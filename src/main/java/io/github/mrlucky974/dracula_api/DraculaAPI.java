package io.github.mrlucky974.dracula_api;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import io.github.mrlucky974.dracula_api.api.ModID;
import io.github.mrlucky974.dracula_api.test.ExampleBlockEntityTypeRegistry;
import io.github.mrlucky974.dracula_api.test.ExampleBlockRegistry;
import io.github.mrlucky974.dracula_api.test.ExampleItemRegistry;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ModID("dracula_api")
public class DraculaAPI extends ModEntrypoint {
    public static final Logger LOGGER = LoggerFactory.getLogger("Dracula API");

    @Override
    protected void onModInitialize() {
        
    }
}
