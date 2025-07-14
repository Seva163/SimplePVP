package com.ayoree.simplepvp.events;

import com.ayoree.simplepvp.Config;
import com.ayoree.simplepvp.features.PVPTimer;
import com.ayoree.simplepvp.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class UseItemEvent implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    private void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        Player player = event.getPlayer();
        if (PVPTimer.isInPVP(player)
                && Config.blockedItems.contains(event.getItem().getType())
        ) {
            Util.sendMessage(player, Config.MSG_ON_PVP_ITEM);
            event.setCancelled(true);
        }
    }
}
