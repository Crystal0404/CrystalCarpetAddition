package crystal0404.crystalcarpetaddition.network.CCANetworkProtocol;

import crystal0404.crystalcarpetaddition.CCASettings;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class CCANetworkProtocolServer {
    public static void playerJoinGame(ServerPlayNetworkHandler handler) {
        if (!CCASettings.CCANetworkProtocol) return;

        if (!(handler.getPlayer() instanceof ServerPlayerEntity)) return;
    }

    public static void server(
            MinecraftServer server,
            ServerPlayerEntity player,
            ServerPlayNetworkHandler handler,
            PacketByteBuf buf,
            PacketSender sender
    ) {

    }
}
