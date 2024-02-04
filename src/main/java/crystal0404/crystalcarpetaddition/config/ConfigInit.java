package crystal0404.crystalcarpetaddition.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crystal0404.crystalcarpetaddition.CrystalCarpetAddition;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

public class ConfigInit {
    private static final String file_path = FabricLoader.getInstance().getConfigDir() + "/CrystalCarpetAddition/CrystalCarpetAddition.json";
    private static final String dir_path = FabricLoader.getInstance().getConfigDir() + "/CrystalCarpetAddition";
    public static void config_init() throws IOException {
        // 创建配置文件夹和文件
        File dir = new File(dir_path);
        if (dir.mkdir()){
            CrystalCarpetAddition.LOGGER.info("The configuration folder is created!");
        }
        File config = new File(file_path);
        if (config.createNewFile()){
            CrystalCarpetAddition.LOGGER.info("The configuration file has been generated!");
            // 序列化配置
            Collection<String> empty_config = new ArrayList<>();
            Config config1 = new Config(empty_config, false, false);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // 写入默认配置
            try (Writer writer = new FileWriter(file_path)){
                writer.write(gson.toJson(config1));
            }
        }
    }
}
