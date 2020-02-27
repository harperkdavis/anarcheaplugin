package main.java.io.github.anarchea.shop;

import main.java.io.github.anarchea.utils.ItemMetaStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ShopMenu {

    Inventory gui;
    private int page;
    private Player player;
    private JavaPlugin plugin;

    public ShopMenu (JavaPlugin plugin, Inventory gui, Player player) {
        this.gui = gui;
        this.player = player;
        this.plugin = plugin;

        // Make bottom bar
        // Previous Page
        this.gui.setItem(45, ItemMetaStack.createItemStack(Material.ARROW, 1, ChatColor.WHITE + "Next Page", null));

        this.gui.setItem(46, ItemMetaStack.createItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, ChatColor.BLACK + " ", null));
        // Close
        this.gui.setItem(47, ItemMetaStack.createItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, ChatColor.BLACK + " ", null));
        // Coin Balance
        double coinBalance = plugin.getConfig().getDouble("PlayerData." + player.getUniqueId() + ".Coins");
        this.gui.setItem(48, ItemMetaStack.createItemStack(Material.SUNFLOWER, 1, ChatColor.GOLD + ("Wallet: " + coinBalance + " coins"), null));

        // Order
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Will be shipped to: ");
        lore.add(ChatColor.YELLOW + (player.getLocation().getBlockX() + ", " + player.getLocation().getBlockZ()));
        lore.add(ChatColor.BLACK + " ");
        lore.add(ChatColor.GRAY + "Order Contents: ");

        ItemStack order = ItemMetaStack.createItemStack(Material.CHEST, 1, ChatColor.GREEN + "Order Delivery", lore);

        this.gui.setItem(49, order);

        // Sort
        this.gui.setItem(50, ItemMetaStack.createItemStack(Material.HOPPER, 1, ChatColor.GREEN + "Sort", null));

        this.gui.setItem(51, ItemMetaStack.createItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, ChatColor.BLACK + " ", null));
        this.gui.setItem(52, ItemMetaStack.createItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, ChatColor.BLACK + " ", null));

        //Next Page
        this.gui.setItem(53, ItemMetaStack.createItemStack(Material.ARROW, 1, ChatColor.WHITE + "Next Page", null));
    }




    public Inventory getInventory() {
        return gui;
    }
}
