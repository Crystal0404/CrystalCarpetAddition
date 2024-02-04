package crystal0404.crystalcarpetaddition.events;

import crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol.CCAProtocolClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ClientPlayConnectionEventsJoin implements ClientPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
        CCAProtocolClient.send();
    }
}
