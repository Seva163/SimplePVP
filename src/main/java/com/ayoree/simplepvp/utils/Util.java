package com.ayoree.simplepvp.utils;

import org.bukkit.entity.Player;

public class Util {
    public static void sendMessage(Player player, String msg) {
        if (!msg.isEmpty()) player.sendMessage(msg);
    }
    public static void sendMessage(Player player, String msg, boolean actionBar) {
        if (!msg.isEmpty()) {
            if (actionBar) {
                player.sendActionBar(msg);
            }
            else {
                sendMessage(player, msg);
            }
        }
    }
    public static void sendMessage(Player player, String msg, String placeholder, String value, boolean actionBar) {
        sendMessage(player, msg.replace(placeholder, value), actionBar);
    }

    public static void sendMessage(Player player, String msg, String placeholder, int value, boolean actionBar) {
        sendMessage(player, msg.replace(placeholder, Integer.toString(value)), actionBar);
    }
}
