package com.clipson.cezessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.clipson.cezessentials.features.teleport.TeleportRequest;
import com.clipson.cezessentials.features.teleport.TeleportRequestManager;
import com.clipson.cezessentials.messages.MessageService;

public class TpAcceptCommand implements CEZCommand {

    private final TeleportRequestManager manager;
    private final MessageService messages;

    public TpAcceptCommand(TeleportRequestManager manager, MessageService messages) {
        this.manager = manager;
        this.messages = messages;
    }

    @Override public String getName() { return "tpaccept"; }
    @Override public String getPermission() { return "cezessentials.tpaccept"; }
    @Override public String getDescription() { return "Accept a teleport request"; }

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
        if (requester == null) {
            manager.removeRequest(target);
            messages.send(target, "tpa-none");
            return true;
        }

        requester.teleport(target.getLocation());
        manager.removeRequest(target);

        messages.send(requester, "tpa-accepted");
        messages.send(target, "tpa-accepted");
        return true;
    }

    @Override public List<String> tabComplete(CommandSender sender, String[] args) { return List.of(); }
}
