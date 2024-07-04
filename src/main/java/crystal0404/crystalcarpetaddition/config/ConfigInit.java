/*
 * This file is part of the Crystal Carpet Addition project, licensed under the
 * GNU General Public License v3.0
 *
 * Copyright (C) 2024  Crystal0404 and contributors
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
import crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class ConfigInit {
    private static final Logger LOGGER = CrystalCarpetAdditionMod.LOGGER;
    private static final String file_path = FabricLoader.getInstance().getConfigDir() + "/CrystalCarpetAddition/CrystalCarpetAddition.json";
    private static final String dir_path = FabricLoader.getInstance().getConfigDir() + "/CrystalCarpetAddition";

    @SuppressWarnings("all")
    public static void init() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) return;
        try {
            configInit();
            ReadConfig.readConfig();
        } catch (IOException e) {
            LOGGER.error("WTF???");
            LOGGER.error("There is an unknown error in the configuration file");
            throw new RuntimeException(e);
        }
    }

    private static void configInit() throws IOException {
        // Create configuration folders and files
        File dir = new File(dir_path);
        if (dir.mkdir()) LOGGER.info("The configuration folder is created!");
        // Create and write the initial configuration
        File config = new File(file_path);
        if (config.createNewFile()) {
            LOGGER.info("The configuration file has been generated!");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (Writer writer = new FileWriter(file_path)) {
                writer.write(gson.toJson(new Config(1, new ArrayList<>(), false, true)));
            }
        }
    }
}
