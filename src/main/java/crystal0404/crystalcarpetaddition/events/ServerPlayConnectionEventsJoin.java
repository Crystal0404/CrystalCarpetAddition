package crystal0404.crystalcarpetaddition.events;

import crystal0404.crystalcarpetaddition.CCASettings;
import crystal0404.crystalcarpetaddition.Rule.CCAProtocol.CCAProtocol;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class ServerPlayConnectionEventsJoin implements ServerPlayConnectionEvents.Join{
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        if (CCASettings.CCAProtocol){
            CCAProtocol.send_mod(handler);
        }
    }
}
