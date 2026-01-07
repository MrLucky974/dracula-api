package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import io.github.mrlucky974.dracula_api.api.Registry;
import io.github.mrlucky974.dracula_api.api.util.ModUtil;
import net.minecraft.util.Identifier;

import java.lang.reflect.Modifier;

public abstract class BaseRegistry {

    /**
     * Builds an Identifier for a registry entry using the mod class from @Registry.
     * Detects the calling registry class automatically.
     */
    static Identifier id(String name) {
        Class<?> registryClass = findConcreteRegistryClass();

        Registry registryAnnotation = registryClass.getAnnotation(Registry.class);
        if (registryAnnotation == null) {
            throw new IllegalStateException(
                    "@Registry annotation missing on " + registryClass.getName()
            );
        }

        return ModUtil.id(registryAnnotation.value(), name);
    }

    private static Class<?> findConcreteRegistryClass() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            try {
                Class<?> cls = Class.forName(element.getClassName());

                // Must be a registry
                if (!BaseRegistry.class.isAssignableFrom(cls)) continue;

                // Skip the base class itself
                if (cls == BaseRegistry.class) continue;

                // Skip abstract helper registries
                if (Modifier.isAbstract(cls.getModifiers())) continue;

                // Must actually declare @Registry
                if (!cls.isAnnotationPresent(Registry.class)) continue;

                return cls;
            } catch (ClassNotFoundException ignored) {
            }
        }

        throw new IllegalStateException(
                "Unable to determine concrete registry class"
        );
    }
}