package io.github.mrlucky974.dracula_api.api.util;

import io.github.mrlucky974.dracula_api.api.ModID;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ModUtil {
    private static final Map<Class<?>, String> MOD_IDS = new ConcurrentHashMap<>();

    private ModUtil() {}

    // register a mod class with its annotation
    public static void register(Class<?> modClass) {
        ModID annotation = modClass.getAnnotation(ModID.class);
        if (annotation == null) throw new IllegalStateException("ModID annotation missing!");
        MOD_IDS.put(modClass, annotation.value());
    }

    public static String modId(Class<?> modClass) {
        String modId = MOD_IDS.get(modClass);
        if (modId == null) {
            // fallback to annotation without registration
            ModID annotation = modClass.getAnnotation(ModID.class);
            if (annotation != null) {
                modId = annotation.value();
            } else {
                throw new IllegalStateException("Mod class not registered and no ModID annotation: " + modClass);
            }
        }
        return modId;
    }

    public static Identifier id(Class<?> modClass, String name) {
        String modId = modId(modClass);
        return Identifier.of(modId, name);
    }
}
