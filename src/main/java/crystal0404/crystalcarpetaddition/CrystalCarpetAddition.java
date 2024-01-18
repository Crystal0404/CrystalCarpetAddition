package crystal0404.crystalcarpetaddition;

import crystal0404.crystalcarpetaddition.config.Regconfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.hendrixshen.magiclib.carpet.impl.WrappedSettingManager;

public class CrystalCarpetAddition implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(CCAReference.getModName());
    @Override
    public void onInitialize() {
        Regconfig.reg_config();
        WrappedSettingManager.register(
                CCAReference.getModIdentifier(),
                CCAExtension.getSettingsManager(),
                new CCAExtension()
        );
    }
}
