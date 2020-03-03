package main.java.io.github.anarchea.shop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIHandler implements Listener {

    public Map<Player, ShopMenu> playerShopInventories = new HashMap<>();
    public Map<Player, Inventory> playerPackageInventories = new HashMap<>();
    public Map<Player, Player> playerDelivery = new HashMap<>();

    private JavaPlugin plugin;

    public GUIHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void inventoryClicked(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (playerShopInventories.get(player) != null) {
            if (e.getInventory() == playerShopInventories.get(player).gui) {
                playerShopInventories.get(player).registerClick(e.getInventory(), e.getRawSlot(), e.getCurrentItem(), e.getClick());
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inventoryClosed(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        if (playerShopInventories.get(player) != null) {
            playerShopInventories.put(player, null);
        }

        if (playerPackageInventories.get(player) != null) {
            // Send package
            if (!isEmptyInventory(playerPackageInventories.get(player))) {
                player.sendMessage(ChatColor.GREEN + "Sending package! It will be delivered in 2 minutes!");
                List<ItemStack> contents = Arrays.asList(playerPackageInventories.get(player).getContents());
                new Delivery(contents, playerDelivery.get(player).getEyeLocation(), playerDelivery.get(player), player.getName()).runTaskLater(plugin, 2400);
            } else {
                player.sendMessage(ChatColor.RED + "Package was not delivered (EMPTY_PACKAGE)");
            }

            playerDelivery.put(player, null);
            playerPackageInventories.put(player, null);
        }
    }

    boolean isEmptyInventory(Inventory i) {
        for(ItemStack it : i.getContents())
        {
            if(it != null) {
                return false;
            }
        }
        return true;
    }

}
