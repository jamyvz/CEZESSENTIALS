package com.clipson.cezessentials.commands;

import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.clipson.cezessentials.features.warp.WarpManager;
import com.clipson.cezessentials.messages.MessageService;

public class DelWarpCommand implements CEZCommand {

    private final WarpManager warpManager;
    private final MessageService messages;

    public DelWarpCommand(WarpManager warpManager, MessageService messages) {
        this.warpManager = warpManager;
        this.messages = messages;
    }

    @Override public String getName() { return "delwarp"; }
    @Override public String getPermission() { return "cezessentials.delwarp"; }
    @Override public String getDescription() { return "Delete a warp"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            messages.send(sender, "players-only");
            return true;
        }
        if (args.length != 1) {
            messages.send(player, "invalid-usage", Map.of("usage", "/delwarp <name>"));
            return true;
        }

        boolean removed = warpManager.deleteWarp(args[0]);
        if (!removed) {
            messages.send(player, "warp-not-found");
            return true;
        }

        messages.send(player, "warp-deleted");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) return warpManager.getWarpNames().stream().toList();
        return List.of();
    }
}
