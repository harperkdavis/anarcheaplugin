package main.java.io.github.anarchea;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EventManager implements Listener {

    private JavaPlugin plugin;

    EventManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        // Check if New player
        if (plugin.getConfig().get("playerData." + e.getPlayer().getUniqueId()) == null) {
            e.setJoinMessage(ChatColor.YELLOW + "Welcome " + e.getPlayer().getName() + " to the server!");

            // Create player in config.yml
            plugin.getConfig().set("playerData." + e.getPlayer().getUniqueId() + ".coins", 500);

            e.getPlayer().getInventory().addItem(new ItemStack(Material.RED_BED, 1));
            e.getPlayer().getInventory().addItem(new ItemStack(Material.ENDER_CHEST, 1));
            e.getPlayer().getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));


            plugin.saveConfig();
            return;
        }

        e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + " joined.");
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {

        e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + " left.");

    }

    @EventHandler
    public void onCreateVehicle(VehicleCreateEvent e) {
        if (e.getVehicle() instanceof Minecart) {
            Minecart minecart = (Minecart) e.getVehicle();

            minecart.setMaxSpeed(32);
        }

    }

    @EventHandler
    public void playerGetXp(PlayerExpChangeEvent e) {
        e.setAmount((int) (e.getAmount() * 1.5));
    }


}
