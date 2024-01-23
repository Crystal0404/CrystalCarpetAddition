package crystal0404.crystalcarpetaddition.Rule.CCAProtocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crystal0404.crystalcarpetaddition.config.ReadConfig;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class CCAProtocol {
    public static void send_mod(ServerPlayNetworkHandler handler){
        // 假人判断
        String tag = handler.player.toString().substring(0, handler.player.toString().indexOf("["));
        if (tag.equals("EntityPlayerMPFake")){
            return;
        }

        // 发送失败就踢出游戏
        if (!ServerPlayNetworking.canSend(handler.player, CCANetwork.HELLO)){
            handler.disconnect(Text.literal("\nPlease install CrystalCarpetAddition!\n")
                    .setStyle(Style.EMPTY.withColor(0x55FFFF))
                    .append(Text.literal("https://modrinth.com/mod/crystalcarpetaddition")
                            .setStyle(Style.EMPTY.withColor(0x55FF55).withUnderline(true))));
            return;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String black_mod = gson.toJson(new Json(ReadConfig.MOD_BLACK_LIST));
        PacketByteBuf buf = PacketByteBufs.create().writeString(black_mod);
        ServerPlayNetworking.send(handler.player, CCANetwork.HELLO, buf);
    }
}
