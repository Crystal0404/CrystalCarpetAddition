package crystal0404.crystalcarpetaddition;

import lombok.Getter;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CCAReference {
    @Getter
    private static final String modIdentifier = "@MOD_IDENTIFIER@";
    @Getter
    private static final String modIdentifierCurrent = "@MOD_IDENTIFIER@-@MINECRAFT_VERSION_IDENTIFY@";
    @Getter
    private static final String modName = "@MOD_NAME@";
    @Getter
    private static final String modNameCurrent = FabricLoader.getInstance().getModContainer(modIdentifierCurrent).orElseThrow(RuntimeException::new).getMetadata().getName();
    @Getter
    private static final String modVersion = FabricLoader.getInstance().getModContainer(modIdentifierCurrent).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
    @Getter
    private static final Logger logger = LogManager.getLogger(modIdentifier);
}
