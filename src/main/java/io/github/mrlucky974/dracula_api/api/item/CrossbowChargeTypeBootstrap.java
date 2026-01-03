package io.github.mrlucky974.dracula_api.api.item;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.Locale;

@ApiStatus.Internal
public final class CrossbowChargeTypeBootstrap {
    private static final String ENTRYPOINT =
            "dracula_api:crossbow_charge_types";

    private CrossbowChargeTypeBootstrap() {}

    public static void build() {
        FabricLoader.getInstance()
                .getEntrypoints(ENTRYPOINT, CrossbowChargeTypeProvider.class);

        CrossbowChargeTypeInternals.freeze();

        var builder = ClassTinkerers.enumBuilder(
                "net.minecraft.item.CrossbowItem$ChargeType",
                String.class
        );

        for (CrossbowChargeType type : CrossbowChargeTypeBootstrap.values()) {
            String name = type.asString();
            builder.addEnum(name.toUpperCase(Locale.ROOT), name);
        }

        builder.build();
    }

    public static List<CrossbowChargeType> values() {
        return CrossbowChargeTypeInternals.values();
    }
}