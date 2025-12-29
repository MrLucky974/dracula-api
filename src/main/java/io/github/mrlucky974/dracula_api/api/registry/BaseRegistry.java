package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import io.github.mrlucky974.dracula_api.api.Initializable;

public abstract class BaseRegistry implements Initializable {
    protected final ModEntrypoint modEntrypoint;

    public BaseRegistry(ModEntrypoint modEntrypoint) {
        this.modEntrypoint = modEntrypoint;
    }
}
