package com.clipson.cezessentials.messages;

import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MessageService {

    private static final MiniMessage MM = MiniMessage.miniMessage();

    private FileConfiguration config;

    public MessageService(FileConfiguration config) {
        this.config = config;
    }

    /** Reload config reference without replacing this service instance. */
    public void reload(FileConfiguration config) {
        this.config = config;
    }

    public void send(CommandSender sender, String key) {
        String prefix = config.getString("messages.prefix", "");
        String raw = config.getString("messages." + key);
        if (raw == null) return;

        sender.sendMessage(parse(prefix + raw));
    }

    public void send(CommandSender sender, String key, Map<String, String> placeholders) {
        String prefix = config.getString("messages.prefix", "");
        String raw = config.getString("messages." + key);
        if (raw == null) return;

        for (var entry : placeholders.entrySet()) {
            raw = raw.replace("<" + entry.getKey() + ">", entry.getValue());
        }

        sender.sendMessage(parse(prefix + raw));
    }

    private Component parse(String input) {
        return MM.deserialize(input);
    }
}
