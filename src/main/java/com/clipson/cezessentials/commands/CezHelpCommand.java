package com.clipson.cezessentials.commands;

import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;

import com.clipson.cezessentials.messages.MessageService;

public class CezHelpCommand implements CEZCommand {

    private final CommandRegistrar registrar;
    private final MessageService messages;

    public CezHelpCommand(CommandRegistrar registrar, MessageService messages) {
        this.registrar = registrar;
        this.messages = messages;
    }

    @Override
    public String getName() {
        return "cezhelp";
    }

    @Override
    public String getPermission() {
        return "cezessentials.help";
    }

    @Override
    public String getDescription() {
        return "Show CEZESSENTIALS commands";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        messages.send(sender, "help-header");

        for (Map.Entry<String, CEZCommand> entry : registrar.getCommands().entrySet()) {
            CEZCommand cmd = entry.getValue();

            String perm = cmd.getPermission();
            if (perm != null && !perm.isEmpty() && !sender.hasPermission(perm)) {
                continue;
            }

            messages.send(
                    sender,
                    "help-line",
                    Map.of(
                            "command", "/" + cmd.getName(),
                            "description", cmd.getDescription()
                    )
            );
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return List.of();
    }
}
