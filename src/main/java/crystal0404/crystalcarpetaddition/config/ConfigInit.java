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
import com.google.gson.GsonBuilder;
import crystal0404.crystalcarpetaddition.CCAReference;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

public class ConfigInit {
    private static final String file_path = FabricLoader.getInstance().getConfigDir() + "/CrystalCarpetAddition/CrystalCarpetAddition.json";
    private static final String dir_path = FabricLoader.getInstance().getConfigDir() + "/CrystalCarpetAddition";
    public static void config_init() throws IOException {
        // Create configuration folders and files
        File dir = new File(dir_path);
        if (dir.mkdir()){
            CCAReference.getLogger().info("The configuration folder is created!");
        }
        File config = new File(file_path);
        if (config.createNewFile()){
            CCAReference.getLogger().info("The configuration file has been generated!");
            // Serialization configuration
            Collection<String> empty_config = new ArrayList<>();
            Config config1 = new Config(empty_config, false, false);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // Write to the default configuration
            try (Writer writer = new FileWriter(file_path)){
                writer.write(gson.toJson(config1));
            }
        }
    }
}
