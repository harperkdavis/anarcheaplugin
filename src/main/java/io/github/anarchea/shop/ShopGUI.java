package main.java.io.github.anarchea.shop;

import main.java.io.github.anarchea.command.ShopCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class ShopGUI implements Listener {

    public Map<Player, ShopMenu> playerInventories = new HashMap<>();

    private JavaPlugin plugin;

    public ShopGUI(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void inventoryClicked(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (playerInventories.get(player) != null) {
            if (e.getInventory() == playerInventories.get(player).gui) {
                playerInventories.get(player).registerClick(e.getInventory(), e.getRawSlot(), e.getCurrentItem(), e.getClick());
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inventoryClosed(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        playerInventories.put(player, null);
    }

}
