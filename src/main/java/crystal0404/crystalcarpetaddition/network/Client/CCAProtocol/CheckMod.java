package crystal0404.crystalcarpetaddition.network.Client.CCAProtocol;

import com.google.gson.Gson;
import crystal0404.crystalcarpetaddition.Rule.CCAProtocol.Json;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.Collection;

public class CheckMod {
    public static void check_mod(MinecraftClient client,
                                 ClientPlayNetworkHandler handler,
                                 PacketByteBuf buf,
                                 PacketSender sender){

        Gson gson = new Gson();
        String json = buf.readString();
        int version = gson.fromJson(json, Json.class).getVersion();
        Collection<String> black_list = gson.fromJson(json, Json.class).getBlackList();
        // 判断版本是否正确
        if (version != 1){
            Text title = Text.literal("CrystalCarpetAddition").setStyle(Style.EMPTY.withColor(0x55FFFF).withBold(true));
            Text reason = Text.literal("The CCA protocol version is incorrect, please try to change the CrystalCarpetAddition.")
                    .setStyle(Style.EMPTY.withColor(0xFF5555))
                    .append(Text.literal("\nhttps://modrinth.com/mod/crystalcarpetaddition")
                            .setStyle(Style.EMPTY.withColor(0x55FF55).withUnderline(true)));
            disconnect(client, title, reason);
            return;
        }
        // 检查是否有黑名单模组
        for (ModContainer allMod : FabricLoader.getInstance().getAllMods()) {
           String mod = allMod.toString().substring(0, allMod.toString().indexOf(" "));
           if (black_list.contains(mod)){
               Text title = Text.literal("CrystalCarpetAddition").setStyle(Style.EMPTY.withColor(0x55FFFF).withBold(true));
               Text reason = Text.literal("You can't use ").setStyle(Style.EMPTY.withColor(0x55FF55))
                       .append(Text.literal(mod).setStyle(Style.EMPTY.withColor(0xFF5555).withUnderline(true)))
                       .append(Text.literal(" in this server!").setStyle(Style.EMPTY.withColor(0x55FF55)));
               disconnect(client, title, reason);
               break;
           }
        }
    }
    // 客户端断开连接
    private static void disconnect(MinecraftClient client, Text title, Text reason){
        client.execute(() -> {
            if (client.world != null){
                client.world.disconnect();
            }
            client.disconnect();
            client.setScreen(new DisconnectedScreen(new MultiplayerScreen(new TitleScreen()), title, reason));
        });
    }
}
