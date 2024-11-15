package com.ayoree.simplepvp.events;

import com.ayoree.simplepvp.Config;
import com.ayoree.simplepvp.features.PVPTimer;
import com.ayoree.simplepvp.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) return;

        Player player = event.getPlayer();
        if (PVPTimer.isInPVP(player)) {
            String[] wordsArray = event.getMessage().split("\\s+");
            if (wordsArray.length > 0) {
                String command = wordsArray[0];
                if (Config.blockedCmds.contains(command)) {
                    Util.sendMessage(player, Config.MSG_ON_PVP_COMMAND);
                    event.setCancelled(true);
                }
            }
        }
    }
}
