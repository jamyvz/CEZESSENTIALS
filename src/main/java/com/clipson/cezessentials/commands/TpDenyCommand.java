package com.clipson.cezessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.clipson.cezessentials.features.teleport.TeleportRequest;
import com.clipson.cezessentials.features.teleport.TeleportRequestManager;
import com.clipson.cezessentials.messages.MessageService;

public class TpDenyCommand implements CEZCommand {

    private final TeleportRequestManager manager;
    private final MessageService messages;

    public TpDenyCommand(TeleportRequestManager manager, MessageService messages) {
        this.manager = manager;
        this.messages = messages;
    }

    @Override public String getName() { return "tpdeny"; }
    @Override public String getPermission() { return "cezessentials.tpdeny"; }
    @Override public String getDescription() { return "Deny a teleport request"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player target)) {
            messages.send(sender, "players-only");
            return true;
        }

        TeleportRequest request = manager.getRequest(target);
        if (request == null) {
            messages.send(target, "tpa-none");
            return true;
        }

        Player requester = manager.getRequester(request);
        if (requester != null) {
            messages.send(requester, "tpa-denied");
        }

        manager.removeRequest(target);
        messages.send(target, "tpa-denied");
        return true;
    }

    @Override public List<String> tabComplete(CommandSender sender, String[] args) { return List.of(); }
}
