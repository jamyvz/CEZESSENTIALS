package com.clipson.cezessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.clipson.cezessentials.features.home.HomeManager;
import com.clipson.cezessentials.messages.MessageService;

public class DelHomeCommand implements CEZCommand {

    private final HomeManager homeManager;
    private final MessageService messages;

    public DelHomeCommand(HomeManager homeManager, MessageService messages) {
        this.homeManager = homeManager;
        this.messages = messages;
    }

    @Override public String getName() { return "delhome"; }
    @Override public String getPermission() { return "cezessentials.delhome"; }
    @Override public String getDescription() { return "Delete your home"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            messages.send(sender, "players-only");
            return true;
        }

        boolean removed = homeManager.deleteHome(player.getUniqueId());
        if (!removed) {
            messages.send(player, "home-not-set");
            return true;
        }

        messages.send(player, "home-deleted");
        return true;
    }

    @Override public List<String> tabComplete(CommandSender sender, String[] args) { return List.of(); }
}
