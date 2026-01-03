package io.github.mrlucky974.dracula_api;

import io.github.mrlucky974.dracula_api.api.item.CrossbowChargeTypeBootstrap;

public class EarlyRiser implements Runnable {
    @Override
    public void run() {
        CrossbowChargeTypeBootstrap.build();
    }
}
