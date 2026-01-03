package io.github.mrlucky974.dracula_api.api.serialization.codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;

import java.util.Set;
import java.util.UUID;

public interface ExtendedCodec<A> {
    PrimitiveCodec<UUID> UUID = new PrimitiveCodec<>() {
        @Override
        public <T> DataResult<UUID> read(final DynamicOps<T> ops, final T input) {
            DataResult<String> stringDataResult = ops.getStringValue(input);
            return stringDataResult.map(java.util.UUID::fromString);
        }

        @Override
        public <T> T write(final DynamicOps<T> ops, final UUID value) {
            return ops.createString(value.toString());
        }

        @Override
        public String toString() {
            return "String";
        }
    };

    static <A> Codec<Set<A>> setOf(Codec<A> codec) {
        return set(codec);
    }

    static <A> Codec<Set<A>> set(final Codec<A> elementCodec) {
        return set(elementCodec, 0, Integer.MAX_VALUE);
    }

    static <A> Codec<Set<A>> set(final Codec<A> elementCodec, final int minSize, final int maxSize) {
        return new SetCodec<>(elementCodec, minSize, maxSize);
    }
}
