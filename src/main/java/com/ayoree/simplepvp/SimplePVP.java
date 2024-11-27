package com.ayoree.simplepvp;

import com.ayoree.simplepvp.events.CommandEvent;
import com.ayoree.simplepvp.events.PVPEvent;
import com.ayoree.simplepvp.events.PlayerDiedEvent;
import com.ayoree.simplepvp.events.PlayerLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimplePVP extends JavaPlugin {
    public Config config;

    @Override
    public void onEnable() {
        config = new Config(this);
        config.loadConfig();

        getServer().getPluginManager().registerEvents(new PVPEvent(), this);
        getServer().getPluginManager().registerEvents(new CommandEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDiedEvent(), this);

        getLogger().info("Плагин запущен.");
    }

    @Override
    public void onDisable() { getLogger().info("Плагин выключен."); }
}
