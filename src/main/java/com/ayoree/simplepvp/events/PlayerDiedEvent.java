package com.ayoree.simplepvp.events;

import com.ayoree.simplepvp.features.PVPTimer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDiedEvent implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDied(PlayerDeathEvent event) {
        PVPTimer.remove(event.getPlayer());
    }

}
