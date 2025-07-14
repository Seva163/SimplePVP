package com.ayoree.simplepvp;

import com.ayoree.simplepvp.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    private final Plugin plugin;

    public ReloadCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        new Config(plugin).loadConfig();
        if (commandSender instanceof Player) {
            Util.sendMessage(((Player) commandSender), Config.MSG_PLUGIN_RELOAD);
        } else {
            commandSender.sendMessage(Config.MSG_PLUGIN_RELOAD);
        }
        return true;
    }
}
