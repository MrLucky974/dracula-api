package io.github.mrlucky974.dracula_api.api.registry.client;

import io.github.mrlucky974.dracula_api.api.Initializable;
import io.github.mrlucky974.dracula_api.api.client.ModClientEntrypoint;

public abstract class BaseClientRegistry implements Initializable {
    protected final ModClientEntrypoint modEntrypoint;

    public BaseClientRegistry(ModClientEntrypoint modEntrypoint) {
        this.modEntrypoint = modEntrypoint;
    }
}
