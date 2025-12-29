package io.github.mrlucky974.dracula_api.api.client;

import io.github.mrlucky974.dracula_api.api.AbstractModEntrypoint;
import io.github.mrlucky974.dracula_api.api.Initializable;
import io.github.mrlucky974.dracula_api.api.registry.client.BaseClientRegistry;
import net.fabricmc.api.ClientModInitializer;

public abstract class ModClientEntrypoint extends AbstractModEntrypoint<BaseClientRegistry, ModClientEntrypoint>
        implements ClientModInitializer, Initializable {

    public ModClientEntrypoint(String modId) {
        super(modId);
    }

    @Override
    protected void initRegistry(BaseClientRegistry registry) {
        registry.init();
    }

    @Override
    public final void onInitializeClient() {
        init();
        initializeRegistries();
    }

    public abstract void init();
}