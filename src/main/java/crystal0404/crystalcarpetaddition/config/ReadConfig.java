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

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Deprecated(forRemoval = true)
@SuppressWarnings("removal")
public class ReadConfig {
    private final static Logger LOGGER = CrystalCarpetAdditionMod.LOGGER;
    private static final String file_path = FabricLoader.getInstance().getConfigDir() + "/CrystalCarpetAddition/CrystalCarpetAddition.json";
    private static ImmutableList<String> BLACK_PACKAGES;
    private static boolean CAN_PRINT_MOD;
    private static boolean CAN_KICK;
    private static int VERSION;

    @SuppressWarnings("all")
    public static void readConfig() throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(file_path))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                int i = inputStream.read();
                if (i == -1) break;
                stringBuilder.append((char) i);
            }
            try {
                Gson gson = new Gson();
                Config config = gson.fromJson(stringBuilder.toString(), Config.class);
                BLACK_PACKAGES = ImmutableList.copyOf(config.getBlackPackages());
                CAN_PRINT_MOD = config.isPrintModList();
                CAN_KICK = config.isKick();
                VERSION = config.getVersion();
                configCheck();
            } catch (JsonSyntaxException e) {
                File file = new File(file_path);
                file.delete();
                LOGGER.error("[CCA] Abnormal configuration file read!Looks like your configuration is not correct!");
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("all")
    private static void configCheck() {
        // Check that the relevant regular expression is matched
        // <modId>$className
        for (String blackPackage : getBlackPackages()) {
            if (!blackPackage.matches("^(.{1,63}\\$).+")) {
                LOGGER.error("[CCA] '%s' format is incorrect!".formatted(blackPackage));
                LOGGER.error("[CCA] Regular expressions need to be satisfied: ^(.{1,63}\\$).+");
                throw new RuntimeException("[CCA] Abnormal configuration file read!Looks like your configuration is not correct!");
            }
        }

        // Check the profile version
        if (getVersion() != 1) {
            LOGGER.error("[CCA] The configuration file version is incorrect!");
            File file = new File(file_path);
            file.delete();
            throw new RuntimeException("[CCA] The configuration file version is incorrect!");
        }
    }

    public static boolean isCanPrintMod() {
        return CAN_PRINT_MOD;
    }

    public static boolean isCanKick() {
        return CAN_KICK;
    }

    public static ImmutableList<String> getBlackPackages() {
        return BLACK_PACKAGES;
    }

    public static int getVersion() {
        return VERSION;
    }
}
