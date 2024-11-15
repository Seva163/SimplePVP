package com.ayoree.simplepvp.events;

import com.ayoree.simplepvp.Config;
import com.ayoree.simplepvp.SimplePVP;
import com.ayoree.simplepvp.features.PVPTimer;
import com.ayoree.simplepvp.utils.Util;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PVPEvent implements Listener {
    SimplePVP plugin;

    public PVPEvent(SimplePVP plugin) { this.plugin = plugin; }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onPlayerPVP(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;

        if (event.getEntity() instanceof Player defender) {
            if (event.getDamager() instanceof Player damager) {
                String gamemodeStr = getGamemodeStr(damager);
                if (damager.hasPermission("simplepvp.gamemode." + gamemodeStr)) {
                    if (Config.PVP_TIMER) {
                        if (!damager.hasPermission("simplepvp.timer.bypass")
                                && defender.getGameMode() != GameMode.CREATIVE
                                && defender.getGameMode() != GameMode.SPECTATOR) {
                            PVPTimer pvpDamagerTimer = new PVPTimer(damager);
                            pvpDamagerTimer.startTimer(Config.PVP_TIME);
                        }
                        if (!defender.hasPermission("simplepvp.timer.bypass")
                                && defender.getGameMode() != GameMode.CREATIVE
                                && defender.getGameMode() != GameMode.SPECTATOR) {
                            PVPTimer pvpDamagerTimer = new PVPTimer(defender);
                            pvpDamagerTimer.startTimer(Config.PVP_TIME);
                        }
                    }
                }
                else {
                    event.setCancelled(true);
                    Util.sendMessage(damager, Config.MSG_NO_PERMISSION, true);
                }
            }
        }
    }

    private String getGamemodeStr(Player player) {
        return switch (player.getGameMode()) {
            case SURVIVAL -> "survival";
            case CREATIVE -> "creative";
            case ADVENTURE -> "adventure";
            case SPECTATOR -> "spectator";
        };
    }
}
