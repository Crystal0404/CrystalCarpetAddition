//#if MC < 12002
package crystal0404.crystalcarpetaddition.events;


import crystal0404.crystalcarpetaddition.network.CCANetwork;
import lombok.Setter;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class ClientJoinedTheGame implements ClientPlayConnectionEvents.Join{
    @Setter
    private static String mod_list;
    @Override
    public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
        PacketByteBuf buf = PacketByteBufs.create().writeString(mod_list);
        ClientPlayNetworking.send(CCANetwork.MOD_LIST, buf);
    }
}
//#endif
