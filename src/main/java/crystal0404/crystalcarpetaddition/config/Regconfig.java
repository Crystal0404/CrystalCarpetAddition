package crystal0404.crystalcarpetaddition.config;

import crystal0404.crystalcarpetaddition.CCAReference;

import java.io.IOException;

public class Regconfig {
    public static void reg_config() {
        try {
            ConfigInit.config_init();
            ReadConfig.read_config();
        }catch (IOException e){
            CCAReference.getLogger().warn("Profile exception!");
            throw new RuntimeException(e);
        }

    }
}
