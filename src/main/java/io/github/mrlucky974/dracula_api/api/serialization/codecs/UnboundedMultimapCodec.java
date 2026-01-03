package io.github.mrlucky974.dracula_api.api.serialization.codecs;

import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import org.jspecify.annotations.NonNull;

public record UnboundedMultimapCodec<K, V>(
        Codec<K> keyCodec,
        Codec<V> elementCodec
) implements BaseMultimapCodec<K, V>, Codec<Multimap<K, V>> {
    @Override
    public <T> DataResult<Pair<Multimap<K, V>, T>> decode(final DynamicOps<T> ops, final T input) {
        return ops.getMap(input).setLifecycle(Lifecycle.stable()).flatMap(map -> decode(ops, map)).map(r -> Pair.of(r, input));
    }

    @Override
    public <T> DataResult<T> encode(final Multimap<K, V> input, final DynamicOps<T> ops, final T prefix) {
        return encode(input, ops, ops.mapBuilder()).build(prefix);
    }

    public static <K, V> UnboundedMultimapCodec<K, V> codec(final Codec<K> keyCodec, final Codec<V> elementCodec) {
        return new UnboundedMultimapCodec<>(keyCodec, elementCodec);
    }

    @Override
    public @NonNull String toString() {
        return "UnboundedMultimapCodec[" + keyCodec + " -> " + elementCodec + ']';
    }
}