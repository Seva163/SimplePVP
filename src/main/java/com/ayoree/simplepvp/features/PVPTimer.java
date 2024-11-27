package com.ayoree.simplepvp.features;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PVPTimer {
    private static final Map<Player, ScheduledTimer> actives = new HashMap<>();
    private final Player player;
    ScheduledTimer timer;

    public PVPTimer(Player player) {
        if (PVPTimer.isInPVP(player)) {
            this.player = player;
            this.timer = actives.get(player);
        }
        else {
            this.player = player;
            this.timer = new ScheduledTimer(player);
        }
    }

    public void startTimer(int seconds) {
        actives.put(player, timer);
        timer.startTimer(seconds);
    }

    public static void remove(Player player) {
        if (actives.containsKey(player)) {
            actives.get(player).stop();
            actives.remove(player);
        }
    }

    public static boolean isInPVP(Player player) {
        return actives.containsKey(player);
    }

}
