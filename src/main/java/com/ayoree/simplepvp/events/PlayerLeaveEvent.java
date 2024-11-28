package com.ayoree.simplepvp.events;

import com.ayoree.simplepvp.Config;
import com.ayoree.simplepvp.SimplePVP;
import com.ayoree.simplepvp.features.PVPTimer;
import com.ayoree.simplepvp.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveEvent implements Listener {
    private final SimplePVP plugin;

    public PlayerLeaveEvent(SimplePVP plugin) { this.plugin = plugin; }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (PVPTimer.isInPVP(player)) {
            if (Config.EXIT_KILL) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Util.sendMessage(p, Config.MSG_EXIT_ON_PVP, "{player}", player.getName(), false);
                }
                plugin.getLogger().info(Config.MSG_EXIT_ON_PVP.replace("{player}", player.getName().replaceAll("§[0-9a-f]", "")));
                player.setHealth(0.0);
            }
            PVPTimer.stopAndRemove(player);
        }
    }
}
