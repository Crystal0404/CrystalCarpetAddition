package crystal0404.crystalcarpetaddition.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
@Getter
public class Config {
    @Setter
    private Collection<String> black_list;
    @Setter
    private boolean player_info;

    public Config(Collection<String> black_list, boolean player_info) {
        this.black_list = black_list;
        this.player_info = player_info;
    }

    @Override
    public String toString() {
        return "config{" +
                "black_list=" + black_list +
                ", player_info=" + player_info +
                '}';
    }
}
