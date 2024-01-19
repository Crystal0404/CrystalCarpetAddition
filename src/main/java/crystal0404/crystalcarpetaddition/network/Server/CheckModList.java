package crystal0404.crystalcarpetaddition.network.Server;

//#if MC >= 12002
//$$ import com.google.gson.Gson;
//$$ import crystal0404.crystalcarpetaddition.CrystalCarpetAddition;
//$$ import crystal0404.crystalcarpetaddition.config.ReadConfig;
//$$ import crystal0404.crystalcarpetaddition.network.other.ModList;
//$$ import net.fabricmc.fabric.api.networking.v1.PacketSender;
//$$ import net.minecraft.network.PacketByteBuf;
//$$ import net.minecraft.server.MinecraftServer;
//$$ import net.minecraft.server.network.ServerConfigurationNetworkHandler;
//$$ import net.minecraft.text.Style;
//$$ import net.minecraft.text.Text;
//$$ import net.minecraft.text.TextColor;
//$$
//$$ import java.util.Collection;
//$$
//$$ public class CheckModList {
//$$     public static void check_mod_list(MinecraftServer server, ServerConfigurationNetworkHandler handler,
//$$                                       PacketByteBuf buf, PacketSender sender){
//$$         Gson gson = new Gson();
//$$         String get_mod_json = buf.readString();
//$$         Collection<String> mod_list = gson.fromJson(get_mod_json, ModList.class).getMod_list();
//$$         if (ReadConfig.PLAYER_INFO){
//$$             CrystalCarpetAddition.LOGGER.info(server.getPlayerManager());
//$$             CrystalCarpetAddition.LOGGER.info(get_mod_json);
//$$         }
//$$         for (String string : ReadConfig.MOD_BLACK_LIST) {
//$$             if (mod_list.contains(string)){
//$$                 handler.disconnect(Text.literal(string).setStyle(Style.EMPTY
//$$                         .withColor(TextColor.fromRgb(0xFF5555)))
//$$                         .append(Text.literal(" Cannot be used on this server!")
//$$                                 .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x55FFFF)))));
//$$                 break;
//$$             }
//$$         }
//$$     }
//$$ }
//#else
import com.google.gson.Gson;
import crystal0404.crystalcarpetaddition.CrystalCarpetAddition;
import crystal0404.crystalcarpetaddition.config.ReadConfig;
import crystal0404.crystalcarpetaddition.network.other.ModList;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import java.util.Collection;

public class CheckModList {
    public static void check_mod(MinecraftServer server, ServerPlayerEntity player,
                                 ServerPlayNetworkHandler handler, PacketByteBuf buf,
                                 PacketSender sender){
        Gson gson = new Gson();
        String get_mod_json = buf.readString();
        Collection<String> mod_list = gson.fromJson(get_mod_json, ModList.class).getMod_list();
        if (ReadConfig.PLAYER_INFO){
             CrystalCarpetAddition.LOGGER.info(get_mod_json);
        }
        for (String string : ReadConfig.MOD_BLACK_LIST) {
            if (mod_list.contains(string)) {
                handler.disconnect(Text.literal(string).setStyle(Style.EMPTY
                                .withColor(TextColor.fromRgb(0xFF5555)))
                        .append(Text.literal(" Cannot be used on this server!")
                                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x55FFFF)))));
                break;
            }
        }
    }
}
//#endif