package com.clipson.cezessentials;

import org.bukkit.plugin.java.JavaPlugin;

import com.clipson.cezessentials.core.PluginBootstrap;

public final class CEZEssentialsPlugin extends JavaPlugin {

    private PluginBootstrap bootstrap;

    @Override
    public void onEnable() {
        this.bootstrap = new PluginBootstrap(this);
        bootstrap.enable();
    }

    @Override
    public void onDisable() {
        // Nothing to clean up yet
    }
}
