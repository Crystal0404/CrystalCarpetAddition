package crystal0404.crystalcarpetaddition.config;

import crystal0404.crystalcarpetaddition.CrystalCarpetAddition;

import java.io.IOException;

public class Regconfig {
    public static void reg_config() {
        try {
            ConfigInit.config_init();
            ReadConfig.read_config();
        }catch (IOException e){
            CrystalCarpetAddition.LOGGER.warn("Profile exception!");
            throw new RuntimeException(e);
        }

    }
}
