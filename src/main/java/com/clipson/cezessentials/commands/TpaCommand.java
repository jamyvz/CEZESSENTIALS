package com.clipson.cezessentials.commands;

import com.clipson.cezessentials.features.teleport.TeleportRequestManager;
import com.clipson.cezessentials.messages.MessageService;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class TpaCommand implements CEZCommand {

    private final TeleportRequestManager manager;
    private final MessageService messages;

    public TpaCommand(TeleportRequestManager manager, MessageService messages) {
        this.manager = manager;
        this.messages = messages;
    }

    @Override public String getName() { return "tpa"; }
    @Override public String getPermission() { return "cezessentials.tpa"; }
    @Override public String getDescription() { return "Request to teleport to another player"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            messages.send(sender, "players-only");
            return true;
        }
        if (args.length != 1) {
            messages.send(player, "invalid-usage", Map.of("usage", "/tpa <player>"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || target.equals(player)) {
            messages.send(player, "invalid-usage", Map.of("usage", "/tpa <player>"));
            return true;
        }

        manager.requestTeleport(player, target);

        messages.send(player, "tpa-sent", Map.of("target", target.getName()));
        messages.send(target, "tpa-received", Map.of("player", player.getName()));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            String prefix = args[0].toLowerCase();
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase().startsWith(prefix))
                    .toList();
        }
        return List.of();
    }
}
