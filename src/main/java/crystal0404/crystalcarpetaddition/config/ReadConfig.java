package crystal0404.crystalcarpetaddition.config;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import crystal0404.crystalcarpetaddition.CrystalCarpetAddition;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class ReadConfig {
    public static Collection<String> MOD_BLACK_LIST;
    public static boolean PLAYER_INFO;
    private static final String file_path = FabricLoader.getInstance().getConfigDir() + "/CrystalCarpetAddition/CrystalCarpetAddition.json";
    public static void read_config() throws IOException {
        // 读取配置文件
        try (InputStream inputStream = Files.newInputStream(Paths.get(file_path))){
            StringBuilder stringBuilder = new StringBuilder();
            while (true){
                int i = inputStream.read();
                if (i == -1){
                    break;
                }
                stringBuilder.append((char) i);
            }
            // 反序列化
            Gson gson = new Gson();
            try {
                Config config = gson.fromJson(stringBuilder.toString(), Config.class);
                MOD_BLACK_LIST = config.getBlack_list();
                PLAYER_INFO = config.isPlayer_info();
            }catch (JsonSyntaxException e){
                File f = new File(file_path);
                f.delete();
                CrystalCarpetAddition.LOGGER.error("Abnormal configuration file read!Looks like your configuration is not correct!");
                throw new RuntimeException(e);
            }
        }
    }
}
