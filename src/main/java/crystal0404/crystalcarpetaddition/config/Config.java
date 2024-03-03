package crystal0404.crystalcarpetaddition.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
@Setter
@Getter
class Config {
    private Collection<String> BlackList;
    private boolean GetModList;
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
