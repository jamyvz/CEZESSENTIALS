package com.clipson.cezessentials.features.spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnManager {

    private final FileConfiguration config;

    public SpawnManager(FileConfiguration config) {
        this.config = config;
    }

    public Location getSpawn() {
        double x = config.getDouble("spawn.x");
        double y = config.getDouble("spawn.y");
        double z = config.getDouble("spawn.z");
        float yaw = (float) config.getDouble("spawn.yaw");
        float pitch = (float) config.getDouble("spawn.pitch");

        return new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch);
    }

    public void setSpawn(Location location) {
        config.set("spawn.x", location.getX());
        config.set("spawn.y", location.getY());
        config.set("spawn.z", location.getZ());
        config.set("spawn.yaw", location.getYaw());
        config.set("spawn.pitch", location.getPitch());
    }
}
