package com.clipson.cezessentials.features.warp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class WarpManager {

    private final File file;
    private final FileConfiguration config;

    public WarpManager(File dataFolder) {
        this.file = new File(dataFolder, "warps.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void setWarp(String name, Location location) {
        String path = "warps." + name.toLowerCase();

        config.set(path + ".world", location.getWorld().getName());
        config.set(path + ".x", location.getX());
        config.set(path + ".y", location.getY());
        config.set(path + ".z", location.getZ());
        config.set(path + ".yaw", location.getYaw());
        config.set(path + ".pitch", location.getPitch());

        save();
    }

    public Location getWarp(String name) {
        String path = "warps." + name.toLowerCase();
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

    public boolean deleteWarp(String name) {
        String path = "warps." + name.toLowerCase();
        if (!config.contains(path)) return false;

        config.set(path, null);
        save();
        return true;
    }

    public Set<String> getWarpNames() {
        if (!config.contains("warps")) return Set.of();
        return config.getConfigurationSection("warps").getKeys(false);
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
