package io.github.mrlucky974.dracula_api.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Registry {
    Class<? extends ModEntrypoint> value();
}