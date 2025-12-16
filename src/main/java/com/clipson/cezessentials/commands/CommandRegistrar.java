package com.clipson.cezessentials.commands;

import com.clipson.cezessentials.CEZEssentialsPlugin;
import com.clipson.cezessentials.core.PluginBootstrap;
import com.clipson.cezessentials.features.home.HomeManager;
import com.clipson.cezessentials.features.spawn.SpawnManager;
import com.clipson.cezessentials.features.teleport.TeleportRequestManager;
import com.clipson.cezessentials.features.warp.WarpManager;
import com.clipson.cezessentials.messages.MessageService;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRegistrar implements CommandExecutor, TabCompleter {

    private final Map<String, CEZCommand> commands = new HashMap<>();
    private final MessageService messages;

    public CommandRegistrar(
            CEZEssentialsPlugin plugin,
            MessageService messages,
            PluginBootstrap bootstrap,
            SpawnManager spawnManager,
            HomeManager homeManager,
            WarpManager warpManager,
            TeleportRequestManager teleportManager
    ) {
        this.messages = messages;

        register(new CezHelpCommand(this, messages));
        register(new CezReloadCommand(bootstrap, messages));

        register(new SetSpawnCommand(spawnManager, messages));
        register(new SpawnCommand(spawnManager, messages));

        register(new SetHomeCommand(homeManager, messages));
        register(new HomeCommand(homeManager, messages));
        register(new DelHomeCommand(homeManager, messages));

        register(new SetWarpCommand(warpManager, messages));
        register(new WarpCommand(warpManager, messages));
        register(new DelWarpCommand(warpManager, messages));

        register(new TpaCommand(teleportManager, messages));
        register(new TpAcceptCommand(teleportManager, messages));
        register(new TpDenyCommand(teleportManager, messages));

        for (String name : commands.keySet()) {
            PluginCommand cmd = plugin.getCommand(name);
            if (cmd != null) {
                cmd.setExecutor(this);
                cmd.setTabCompleter(this);
            }
        }
    }

    public Map<String, CEZCommand> getCommands() {
        return Map.copyOf(commands);
    }

    private void register(CEZCommand command) {
        commands.put(command.getName().toLowerCase(), command);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        CEZCommand cezCommand = commands.get(command.getName().toLowerCase());
        if (cezCommand == null) return true;

        String perm = cezCommand.getPermission();
        if (perm != null && !perm.isEmpty() && !sender.hasPermission(perm)) {
            messages.send(sender, "no-permission");
            return true;
        }

        return cezCommand.execute(sender, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        CEZCommand cezCommand = commands.get(command.getName().toLowerCase());
        return cezCommand != null ? cezCommand.tabComplete(sender, args) : List.of();
    }
}
