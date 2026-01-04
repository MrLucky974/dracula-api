package io.github.mrlucky974.dracula_api.api.item;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

import java.util.Locale;

public final class CrossbowChargeType implements StringIdentifiable {
    private final Identifier identifier;

    private CrossbowChargeType(Identifier identifier) {
        this.identifier = identifier;
    }

    static CrossbowChargeType of(Identifier identifier) {
        return new CrossbowChargeType(identifier);
    }

    public CrossbowItem.ChargeType asEnumValue() {
        return ClassTinkerers.getEnum(CrossbowItem.ChargeType.class, identifier.toUnderscoreSeparatedString().toUpperCase(Locale.ROOT));
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Identifier getModelId() {
        return getModelId(this);
    }

    public static Identifier getModelId(CrossbowChargeType type) {
        return type.getIdentifier().withPrefixedPath("item/crossbow_");
    }

    @Override
    public String asString() {
        return identifier.toUnderscoreSeparatedString().toLowerCase(Locale.ROOT);
    }
}
