package crystal0404.crystalcarpetaddition.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
@Getter
public class Config {
    @Setter
    private Collection<String> black_list;

    public Config(Collection<String> black_list) {
        this.black_list = black_list;
    }

    @Override
    public String toString() {
        return "Config{" +
                "black_list=" + black_list +
                '}';
    }
}
