package com.clipson.cezessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.clipson.cezessentials.features.home.HomeManager;
import com.clipson.cezessentials.messages.MessageService;

public class SetHomeCommand implements CEZCommand {

    private final HomeManager homeManager;
    private final MessageService messages;

    public SetHomeCommand(HomeManager homeManager, MessageService messages) {
        this.homeManager = homeManager;
        this.messages = messages;
    }

    @Override public String getName() { return "sethome"; }
    @Override public String getPermission() { return "cezessentials.sethome"; }
    @Override public String getDescription() { return "Set your home"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            messages.send(sender, "players-only");
            return true;
        }
        homeManager.setHome(player.getUniqueId(), player.getLocation());
        messages.send(player, "home-set");
        return true;
    }

    @Override public List<String> tabComplete(CommandSender sender, String[] args) { return List.of(); }
}
