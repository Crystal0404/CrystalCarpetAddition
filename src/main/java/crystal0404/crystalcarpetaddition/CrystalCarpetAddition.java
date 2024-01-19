package crystal0404.crystalcarpetaddition;

import crystal0404.crystalcarpetaddition.config.Regconfig;
import crystal0404.crystalcarpetaddition.events.JoinedTheGame;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.hendrixshen.magiclib.carpet.impl.WrappedSettingManager;
//#if MC>=12002
//$$ import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents;
//$$ import crystal0404.crystalcarpetaddition.events.JoinedTheGame;
//#endif



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
        //#if MC>=12002
        //$$ ServerConfigurationConnectionEvents.CONFIGURE.register(new JoinedTheGame());
        //#else
        ServerPlayConnectionEvents.JOIN.register(new JoinedTheGame());
        //#endif
        CCANetwork.registerC2SPacks();
    }
}
