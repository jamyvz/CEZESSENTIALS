package com.clipson.cezessentials.commands;

import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.clipson.cezessentials.features.warp.WarpManager;
import com.clipson.cezessentials.messages.MessageService;

public class SetWarpCommand implements CEZCommand {

    private final WarpManager warpManager;
    private final MessageService messages;

    public SetWarpCommand(WarpManager warpManager, MessageService messages) {
        this.warpManager = warpManager;
        this.messages = messages;
    }

    @Override public String getName() { return "setwarp"; }
    @Override public String getPermission() { return "cezessentials.setwarp"; }
    @Override public String getDescription() { return "Set a warp"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            messages.send(sender, "players-only");
            return true;
        }
        if (args.length != 1) {
            messages.send(player, "invalid-usage", Map.of("usage", "/setwarp <name>"));
            return true;
        }

        warpManager.setWarp(args[0], player.getLocation());
        messages.send(player, "warp-set", Map.of("name", args[0]));
        return true;
    }

    @Override public List<String> tabComplete(CommandSender sender, String[] args) { return List.of(); }
}
