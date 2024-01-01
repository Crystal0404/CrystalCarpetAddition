package crystal0404.crystalcarpetaddition;

import top.hendrixshen.magiclib.carpet.api.annotation.Rule;

public class CCASettings {
    public static final String CCA = "CrystalCarpetAddition";
    @Rule(categories = CCA)
    //#if MC >=12002
    public static boolean MagicBox = false;
    //#else
    //$$ public static boolean MagicBox = true;
    //#endif
    @Rule(categories = CCA)
    public static boolean CEnderPearlChunkLoading = false;
}
