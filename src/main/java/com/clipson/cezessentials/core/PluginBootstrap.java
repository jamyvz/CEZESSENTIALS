package com.clipson.cezessentials.core;

import com.clipson.cezessentials.CEZEssentialsPlugin;
import com.clipson.cezessentials.commands.CommandRegistrar;
import com.clipson.cezessentials.features.home.HomeManager;
import com.clipson.cezessentials.features.spawn.SpawnManager;
import com.clipson.cezessentials.features.teleport.TeleportRequestManager;
import com.clipson.cezessentials.features.warp.WarpManager;
import com.clipson.cezessentials.messages.MessageService;

public class PluginBootstrap {

    private final CEZEssentialsPlugin plugin;

    private MessageService messageService;

    private SpawnManager spawnManager;
    private HomeManager homeManager;
    private WarpManager warpManager;
    private TeleportRequestManager teleportManager;

    public PluginBootstrap(CEZEssentialsPlugin plugin) {
        this.plugin = plugin;
    }

    public void enable() {
        plugin.saveDefaultConfig();

        loadServices();

        new CommandRegistrar(
                plugin,
                messageService,
                this,
                spawnManager,
                homeManager,
                warpManager,
                teleportManager
        );

        plugin.getLogger().info("CEZESSENTIALS enabled.");
    }

    public void reload() {
        plugin.reloadConfig();
        messageService.reload(plugin.getConfig());
    }

    private void loadServices() {
        this.messageService = new MessageService(plugin.getConfig());

        this.spawnManager = new SpawnManager(plugin.getConfig());
        this.homeManager = new HomeManager(plugin.getDataFolder());
        this.warpManager = new WarpManager(plugin.getDataFolder());
        this.teleportManager = new TeleportRequestManager();
    }

    public MessageService getMessageService() {
        return messageService;
    }
}
