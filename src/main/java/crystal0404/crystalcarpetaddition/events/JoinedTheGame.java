//#if MC >= 12002
package crystal0404.crystalcarpetaddition.events;

import crystal0404.crystalcarpetaddition.CCASettings;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.fabric.api.networking.v1.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerConfigurationNetworkHandler;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

public class JoinedTheGame implements ServerConfigurationConnectionEvents.Configure{


    @Override
    public void onSendConfiguration(ServerConfigurationNetworkHandler handler, MinecraftServer server) {
        System.out.println(handler.getDebugProfile());
        if (CCASettings.CCANetworkProtocol) {
            // 判断是否是carpet假人
            if (ServerConfigurationNetworking.canSend(handler, CCANetwork.HELLO)) {
                    // 发一个空包, 传输相关字段
                    ServerConfigurationNetworking.send(handler, CCANetwork.HELLO, PacketByteBufs.create());
            } else {
                    // 断开连接
                handler.disconnect(Text.literal("Please install CrystalCarpetAddition!\n")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x55FFFF)))
                        .append(Text.literal("https://modrinth.com/mod/crystalcarpetaddition")
                                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x55FF55))
                                            .withUnderline(true))));

            }
        }
    }
}
//#endif