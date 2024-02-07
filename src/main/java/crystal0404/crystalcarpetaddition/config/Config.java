package crystal0404.crystalcarpetaddition.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
@Getter
class Config {
    @Setter
    private Collection<String> BlackList;
    @Setter
    private boolean GetModList;
    @Setter
    private boolean Regex;

    public Config(Collection<String> black_list, boolean getModList, boolean regex) {
        this.BlackList = black_list;
        GetModList = getModList;
        Regex = regex;
    }

    @Override
    public String toString() {
        return "Config{" +
                "BlackList=" + BlackList +
                ", GetModList=" + GetModList +
                ", Regex=" + Regex +
                '}';
    }
}
