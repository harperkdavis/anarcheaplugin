package main.java.io.github.anarchea;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

class EventManager implements Listener {

    private JavaPlugin plugin;

    EventManager (JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        // Check if New player
        if (!Bukkit.getOfflinePlayer(e.getPlayer().getUniqueId()).hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.YELLOW + "Welcome " + e.getPlayer().getName() + " to the server!");

            // Create player in config.yml
            plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".Coins", 500);

            return;
        }

        e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + " joined.");


    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {

        e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + " left.");

    }

}
