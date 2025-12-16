package com.clipson.cezessentials.features.home;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class HomeManager {

    private final File file;
    private final FileConfiguration config;

    public HomeManager(File dataFolder) {
        this.file = new File(dataFolder, "homes.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void setHome(UUID uuid, Location location) {
        String path = "players." + uuid;

        config.set(path + ".world", location.getWorld().getName());
        config.set(path + ".x", location.getX());
        config.set(path + ".y", location.getY());
        config.set(path + ".z", location.getZ());
        config.set(path + ".yaw", location.getYaw());
        config.set(path + ".pitch", location.getPitch());

        save();
    }

    public Location getHome(UUID uuid) {
        String path = "players." + uuid;
        if (!config.contains(path)) return null;

        return new Location(
                Bukkit.getWorld(config.getString(path + ".world")),
                config.getDouble(path + ".x"),
                config.getDouble(path + ".y"),
                config.getDouble(path + ".z"),
                (float) config.getDouble(path + ".yaw"),
                (float) config.getDouble(path + ".pitch")
        );
    }

    public boolean deleteHome(UUID uuid) {
        String path = "players." + uuid;
        if (!config.contains(path)) return false;

        config.set(path, null);
        save();
        return true;
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
