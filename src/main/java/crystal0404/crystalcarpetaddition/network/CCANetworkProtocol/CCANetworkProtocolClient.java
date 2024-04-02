package crystal0404.crystalcarpetaddition.network.CCANetworkProtocol;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class CCANetworkProtocolClient {
    public static void client(
            MinecraftClient client,
            ClientPlayNetworkHandler handler,
            PacketByteBuf buf,
            PacketSender sender
    ) {

    }
}
