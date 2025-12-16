package com.clipson.cezessentials.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface CEZCommand {

    String getName();

    String getPermission();

    String getDescription();

    boolean execute(CommandSender sender, String[] args);

    default List<String> tabComplete(CommandSender sender, String[] args) {
        return List.of();
    }
}
