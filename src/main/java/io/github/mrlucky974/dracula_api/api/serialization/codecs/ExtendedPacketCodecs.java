package io.github.mrlucky974.dracula_api.api.serialization.codecs;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.encoding.StringEncoding;

import java.util.UUID;

public interface ExtendedPacketCodecs {
    PacketCodec<ByteBuf, UUID> UUID = uuid(36);

    static PacketCodec<ByteBuf, UUID> uuid(int maxLength) {
        return new PacketCodec<>() {
            public UUID decode(ByteBuf byteBuf) {
                String value = StringEncoding.decode(byteBuf, maxLength);
                return java.util.UUID.fromString(value);
            }

            public void encode(ByteBuf byteBuf, UUID uuid) {
                StringEncoding.encode(byteBuf, uuid.toString(), maxLength);
            }
        };
    }
}
