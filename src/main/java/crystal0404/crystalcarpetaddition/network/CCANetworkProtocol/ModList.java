package crystal0404.crystalcarpetaddition.network.CCANetworkProtocol;

import java.util.Collection;
import java.util.HashMap;

class ModList {
    private Collection<HashMap<String, String>> ModList;
    private boolean Regex;

    public ModList(Collection<HashMap<String, String>> modList, boolean regex) {
        ModList = modList;
        Regex = regex;
    }

    public ModList(Collection<HashMap<String, String>> modList) {
        ModList = modList;
    }

    @Override
    public String toString() {
        return "ModList{" +
                "ModList=" + ModList +
                ", Regex=" + Regex +
                '}';
    }

    public Collection<HashMap<String, String>> getModList() {
        return ModList;
    }

    public void setModList(Collection<HashMap<String, String>> modList) {
        ModList = modList;
    }

    public boolean isRegex() {
        return Regex;
    }

    public void setRegex(boolean regex) {
        Regex = regex;
    }
}
