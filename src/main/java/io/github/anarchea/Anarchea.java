package main.java.io.github.anarchea;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;

public class Anarchea extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new EventManager(this), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Anarchea successfully enabled!");

        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Anarchea successfully disabled!");
    }
}
