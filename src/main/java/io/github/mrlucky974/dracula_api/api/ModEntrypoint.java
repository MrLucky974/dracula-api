package io.github.mrlucky974.dracula_api.api;

import io.github.mrlucky974.dracula_api.api.registry.BaseRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ModEntrypoint extends AbstractModEntrypoint<BaseRegistry, ModEntrypoint>
        implements ModInitializer, Initializable {

    private final Logger logger;

    public ModEntrypoint(String modId, String modName) {
        super(modId);
        this.logger = LoggerFactory.getLogger(modName);
    }

    @Override
    protected void initRegistry(BaseRegistry registry) {
        registry.init();
    }

    @Override
    public final void onInitialize() {
        init();
        initializeRegistries();
    }

    public abstract void init();

    public Logger logger() {
        return logger;
    }
}