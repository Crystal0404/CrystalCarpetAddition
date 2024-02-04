package crystal0404.crystalcarpetaddition.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
@Getter
class Config {
    @Setter
    private Collection<String> black_list;
    @Setter
    private boolean GetModList;
    @Setter
    private boolean Regex;

    public Config(Collection<String> black_list, boolean getModList, boolean regex) {
        this.black_list = black_list;
        GetModList = getModList;
        Regex = regex;
    }

    @Override
    public String toString() {
        return "Config{" +
                "black_list=" + black_list +
                ", GetModList=" + GetModList +
                ", Regex=" + Regex +
                '}';
    }
}
