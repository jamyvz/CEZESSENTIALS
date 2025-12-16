package com.clipson.cezessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.clipson.cezessentials.features.spawn.SpawnManager;
import com.clipson.cezessentials.messages.MessageService;

public class SetSpawnCommand implements CEZCommand {

    private final SpawnManager spawnManager;
    private final MessageService messages;

    public SetSpawnCommand(SpawnManager spawnManager, MessageService messages) {
        this.spawnManager = spawnManager;
        this.messages = messages;
    }

    @Override public String getName() { return "setspawn"; }
    @Override public String getPermission() { return "cezessentials.setspawn"; }
    @Override public String getDescription() { return "Set the spawn point for the server"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            messages.send(sender, "players-only");
            return true;
        }
        spawnManager.setSpawn(player.getLocation());
        messages.send(player, "spawn-set");
        return true;
    }

    @Override public List<String> tabComplete(CommandSender sender, String[] args) { return List.of(); }
}
