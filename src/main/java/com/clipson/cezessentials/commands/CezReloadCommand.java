package com.clipson.cezessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.clipson.cezessentials.core.PluginBootstrap;
import com.clipson.cezessentials.messages.MessageService;

public class CezReloadCommand implements CEZCommand {

    private final PluginBootstrap bootstrap;
    private final MessageService messages;

    public CezReloadCommand(PluginBootstrap bootstrap, MessageService messages) {
        this.bootstrap = bootstrap;
        this.messages = messages;
    }

    @Override public String getName() { return "cezreload"; }
    @Override public String getPermission() { return "cezessentials.reload"; }
    @Override public String getDescription() { return "Reload CEZESSENTIALS configuration"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        bootstrap.reload();
        messages.send(sender, "reload-complete");
        return true;
    }

    @Override public List<String> tabComplete(CommandSender sender, String[] args) { return List.of(); }
}
