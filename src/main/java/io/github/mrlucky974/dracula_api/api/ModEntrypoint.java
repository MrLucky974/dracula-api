package io.github.mrlucky974.dracula_api.api;

import io.github.mrlucky974.dracula_api.api.util.ModUtil;
import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Set;

public abstract class ModEntrypoint implements ModInitializer {

    protected ModEntrypoint() {
        ModUtil.register(this.getClass());
    }

    protected abstract void onModInitialize();

    @Override
    public final void onInitialize() {
        initRegistries();
        onModInitialize();
    }

    // ----------------------------
    // Registry loading
    // ----------------------------

    /**
     * Automatically finds and loads all @Registry classes under the mod's package.
     * This forces their static fields to initialize.
     */
    protected void initRegistries() {
        Reflections reflections = getReflections();
        Set<Class<?>> registryClasses = reflections.getTypesAnnotatedWith(Registry.class);

        for (Class<?> registryClass : registryClasses) {
            try {
                // Only load non-abstract classes
                if (!Modifier.isAbstract(registryClass.getModifiers())) {
                    Class.forName(registryClass.getName());
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Failed to load registry class: " + registryClass, e);
            }
        }
    }

    @NotNull
    private Reflections getReflections() {
        String basePackage = this.getClass().getPackage().getName();
        return new Reflections(basePackage);
    }

    // ----------------------------
    // Safe mod ID resolution
    // ----------------------------

    public static String modId() {
        Class<?> modClass = getCallingModClass();
        return ModUtil.modId(modClass);
    }

    public static net.minecraft.util.Identifier id(String name) {
        Class<?> modClass = getCallingModClass();
        return ModUtil.id(modClass, name);
    }

    private static Class<?> getCallingModClass() {
        return java.lang.StackWalker.getInstance(java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(frames -> frames
                        .map(java.lang.StackWalker.StackFrame::getDeclaringClass)
                        .filter(ModEntrypoint.class::isAssignableFrom)
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException(
                                "Could not determine calling mod class from the stack"
                        )));
    }
}