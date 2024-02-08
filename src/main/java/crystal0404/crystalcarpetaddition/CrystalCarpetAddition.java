package crystal0404.crystalcarpetaddition;

import crystal0404.crystalcarpetaddition.config.Regconfig;
import net.fabricmc.api.ModInitializer;
import top.hendrixshen.magiclib.carpet.impl.WrappedSettingManager;


public class CrystalCarpetAddition implements ModInitializer {
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
