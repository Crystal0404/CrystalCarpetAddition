/*
 * This file is part of the Crystal Carpet Addition project, licensed under the
 * GNU General Public License v3.0
 *
 * Copyright (C) 2024  Crystal_0404 and contributors
 *
 * Crystal Carpet Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Crystal Carpet Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Crystal Carpet Addition.  If not, see <https://www.gnu.org/licenses/>.
 */

package crystal0404.crystalcarpetaddition.config;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import crystal0404.crystalcarpetaddition.CCAReference;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

@SuppressWarnings("all")
public class ReadConfig {
    public static Collection<String> MOD_BLACK_LIST;
    public static boolean GET_MOD;
    public static boolean REGEX;
    private static final String file_path = FabricLoader.getInstance().getConfigDir() + "/CrystalCarpetAddition/CrystalCarpetAddition.json";
    public static void read_config() throws IOException {
        // Read the configuration file
        try (InputStream inputStream = Files.newInputStream(Paths.get(file_path))){
            StringBuilder stringBuilder = new StringBuilder();
            while (true){
                int i = inputStream.read();
                if (i == -1){
                    break;
                }
                stringBuilder.append((char) i);
            }
            // deserialization
            Gson gson = new Gson();
            try {
                Config config = gson.fromJson(stringBuilder.toString(), Config.class);
                MOD_BLACK_LIST = config.getBlackList();
                GET_MOD = config.isGetModList();
                REGEX = config.isRegex();
            }catch (JsonSyntaxException e){
                File f = new File(file_path);
                f.delete();
                CCAReference.getLogger().error("Abnormal configuration file read!Looks like your configuration is not correct!");
                throw new RuntimeException(e);
            }
        }
    }
}
