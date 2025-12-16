package com.clipson.cezessentials.commands;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.clipson.cezessentials.features.home.HomeManager;
import com.clipson.cezessentials.messages.MessageService;

public class HomeCommand implements CEZCommand {

    private final HomeManager homeManager;
    private final MessageService messages;

    public HomeCommand(HomeManager homeManager, MessageService messages) {
        this.homeManager = homeManager;
        this.messages = messages;
    }

    @Override public String getName() { return "home"; }
    @Override public String getPermission() { return "cezessentials.home"; }
    @Override public String getDescription() { return "Teleport to your home"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            messages.send(sender, "players-only");
            return true;
        }

        Location home = homeManager.getHome(player.getUniqueId());
        if (home == null) {
            messages.send(player, "home-not-set");
            return true;
        }

        player.teleport(home);
        messages.send(player, "home-teleport");
        return true;
    }

    @Override public List<String> tabComplete(CommandSender sender, String[] args) { return List.of(); }
}
