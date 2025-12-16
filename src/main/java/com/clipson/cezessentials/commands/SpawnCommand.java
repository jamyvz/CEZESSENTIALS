package com.clipson.cezessentials.commands;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.clipson.cezessentials.features.spawn.SpawnManager;
import com.clipson.cezessentials.messages.MessageService;

public class SpawnCommand implements CEZCommand {

    private final SpawnManager spawnManager;
    private final MessageService messages;

    public SpawnCommand(SpawnManager spawnManager, MessageService messages) {
        this.spawnManager = spawnManager;
        this.messages = messages;
    }

    @Override public String getName() { return "spawn"; }
    @Override public String getPermission() { return "cezessentials.spawn"; }
    @Override public String getDescription() { return "Teleport to the saved spawn point"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            messages.send(sender, "players-only");
            return true;
        }

        Location spawn = spawnManager.getSpawn();
        player.teleport(spawn);
        messages.send(player, "teleported-spawn");
        return true;
    }

    @Override public List<String> tabComplete(CommandSender sender, String[] args) { return List.of(); }
}
