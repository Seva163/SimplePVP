package com.ayoree.simplepvp.events;

import com.ayoree.simplepvp.Config;
import com.ayoree.simplepvp.features.PVPTimer;
import com.ayoree.simplepvp.utils.Util;
import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class PVPEvent implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    private void onPlayerPVP(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;

        if (event.getEntity() instanceof Player defender) {
            Entity entity = event.getDamager();
            Player damager = null;

            if (entity instanceof Player player) {
                damager = player;
            } else if (entity instanceof Projectile projectile) {
                ProjectileSource source = projectile.getShooter();
                if (source instanceof Player player) {
                    damager = player;
                }
            }

            if (damager != null && damager != defender) {
                String gamemodeStr = damager.getGameMode().name().toLowerCase();
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
}
