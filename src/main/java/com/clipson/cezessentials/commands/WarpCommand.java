package com.clipson.cezessentials.commands;

import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.clipson.cezessentials.features.warp.WarpManager;
import com.clipson.cezessentials.messages.MessageService;

public class WarpCommand implements CEZCommand {

    private final WarpManager warpManager;
    private final MessageService messages;

    public WarpCommand(WarpManager warpManager, MessageService messages) {
        this.warpManager = warpManager;
        this.messages = messages;
    }

    @Override public String getName() { return "warp"; }
    @Override public String getPermission() { return "cezessentials.warp"; }
    @Override public String getDescription() { return "Teleport to a warp"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            messages.send(sender, "players-only");
            return true;
        }
        if (args.length != 1) {
            messages.send(player, "invalid-usage", Map.of("usage", "/warp <name>"));
            return true;
        }

        Location warp = warpManager.getWarp(args[0]);
        if (warp == null) {
            messages.send(player, "warp-not-found");
            return true;
        }

        player.teleport(warp);
        messages.send(player, "warp-teleport", Map.of("name", args[0]));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            String prefix = args[0].toLowerCase();
            return warpManager.getWarpNames().stream()
                    .filter(name -> name.toLowerCase().startsWith(prefix))
                    .toList();
        }
        return List.of();
    }
}
