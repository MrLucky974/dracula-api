package io.github.mrlucky974.dracula_api;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import io.github.mrlucky974.dracula_api.test.ExampleBlockEntityTypeRegistry;
import io.github.mrlucky974.dracula_api.test.ExampleBlockRegistry;
import io.github.mrlucky974.dracula_api.test.ExampleItemRegistry;
import net.minecraft.item.CrossbowItem;

public class DraculaAPI extends ModEntrypoint {
    public static final String MOD_ID = "dracula_api";

    public DraculaAPI() {
        super(MOD_ID, "Dracula API");
    }

    @Override
    public void init() {
        this.logger().info("Hello, it works!");

        addRegistry(ExampleItemRegistry::new);
        addRegistry(ExampleBlockRegistry::new);
        addRegistry(ExampleBlockEntityTypeRegistry::new);
    }
}
