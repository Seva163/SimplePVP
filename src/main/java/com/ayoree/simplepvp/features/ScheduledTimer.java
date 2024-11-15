package com.ayoree.simplepvp.features;

import com.ayoree.simplepvp.Config;
import com.ayoree.simplepvp.utils.Util;
import org.bukkit.entity.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTimer {
    private ScheduledExecutorService scheduler;
    private final Player player;
    private int seconds = Config.PVP_TIME;
    private boolean isRunning = false;

    public ScheduledTimer(Player player) {
        this.player = player;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startTimer(int seconds) {
        this.seconds = seconds;
        if (isRunning) {
            scheduler.shutdownNow();
            scheduler.close();
            scheduler = Executors.newScheduledThreadPool(1);
        }
        else {
            isRunning = true;
        }
        scheduler.scheduleAtFixedRate(this::performTask, 0, 1, TimeUnit.SECONDS);
    }

    private void performTask() {
        if (seconds >= 0) {
            Util.sendMessage(player, Config.MSG_ON_PVP_TIME, "{time}", seconds, true);
            seconds--;
        }
        else {
            Util.sendMessage(player, Config.MSG_END_PVP, true);
            isRunning = false;
            scheduler.shutdownNow();
            PVPTimer.remove(player);
        }
    }
}
