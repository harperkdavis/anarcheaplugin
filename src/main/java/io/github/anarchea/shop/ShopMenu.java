package main.java.io.github.anarchea.shop;

import main.java.io.github.anarchea.utils.ItemMetaStack;
import main.java.io.github.anarchea.utils.ShopConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ShopMenu {

    Inventory gui;
    private int page = 0;
    private Player player;
    private JavaPlugin plugin;
    private int deliveryTicks;
    private List<ItemStack> orderList = new ArrayList<>();

    public ShopMenu (JavaPlugin plugin, Inventory gui, Player player) {
        this.gui = gui;
        this.player = player;
        this.plugin = plugin;
        deliveryTicks = (int) Math.sqrt((player.getLocation().getBlockX() * player.getLocation().getBlockX()) + (player.getLocation().getBlockZ() * player.getLocation().getBlockZ()));
        deliveryTicks *= 3;
        refreshShopMenu(0);
    }

    private void loadBottomBar() {
        // Make bottom bar
        // Previous Page
        this.gui.setItem(45, ItemMetaStack.createItemStack(Material.ARROW, 1, ChatColor.WHITE + "Previous Page", null));

        this.gui.setItem(46, ItemMetaStack.createItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, ChatColor.BLACK + " ", null));
        // Close
        this.gui.setItem(47, ItemMetaStack.createItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, ChatColor.BLACK + " ", null));
        // Coin Balance
        double coinBalance = plugin.getConfig().getDouble("playerData." + player.getUniqueId() + ".coins");
        this.gui.setItem(48, ItemMetaStack.createItemStack(Material.SUNFLOWER, 1, ChatColor.GOLD + ("Wallet: " + coinBalance + " coins"), null));

        // Order
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Will be shipped to: ");
        lore.add(ChatColor.YELLOW + (player.getLocation().getBlockX() + ", " + player.getLocation().getBlockZ()));

        lore.add(ChatColor.GRAY + "Delivery time: ");

        String deliveryTime = ((int)Math.floor(((float)deliveryTicks / 20) / 60) + "m" + (deliveryTicks/20)%60 + "s");

        lore.add(ChatColor.YELLOW + deliveryTime);

        lore.add(ChatColor.GRAY + "Total Cost: ");
        lore.add(ChatColor.GOLD + (totalOrderPrice() + " coins"));

        lore.add(ChatColor.BLACK + " ");
        lore.add(ChatColor.GRAY + "Order Contents: ");

        for (ItemStack is : orderList) {
            lore.add(ChatColor.DARK_GRAY + ItemMetaStack.getItemName(is) + " (x" + is.getAmount() + ")");
        }

        ItemStack orderItem;

        String amount = " (" + orderList.size() + "/27)";

        if (canOrderDelivery()) {
            orderItem = ItemMetaStack.createItemStack(Material.CHEST, 1, ChatColor.GREEN + "Order Delivery" + amount, lore);
        } else {
            orderItem = ItemMetaStack.createItemStack(Material.CHEST, 1, ChatColor.RED + "Order Delivery" + amount, lore);
        }

        this.gui.setItem(49, orderItem);

        // Sort
        this.gui.setItem(50, ItemMetaStack.createItemStack(Material.HOPPER, 1, ChatColor.RED + "Remove Last Item", null));

        this.gui.setItem(51, ItemMetaStack.createItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, ChatColor.BLACK + " ", null));
        this.gui.setItem(52, ItemMetaStack.createItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, ChatColor.BLACK + " ", null));

        //Next Page
        this.gui.setItem(53, ItemMetaStack.createItemStack(Material.ARROW, 1, ChatColor.WHITE + "Next Page", null));

    }

    private void Buy() {

    }

    private boolean canOrderDelivery() {
        double totalPrice = 0;
        for (ItemStack is : orderList) {
            totalPrice += plugin.getConfig().getDouble(ShopConfig.getConfigItemStack(plugin, is) + ".price.buy");
        }
        totalPrice += 10;
        return !(totalPrice > plugin.getConfig().getDouble("playerData." + player.getUniqueId() + ".coins"));
    }

    private double totalOrderPrice() {
        double totalPrice = 0;
        for (ItemStack is : orderList) {
            totalPrice += plugin.getConfig().getDouble(ShopConfig.getConfigItemStack(plugin, is) + ".price.buy");
        }
        totalPrice += 10;
        return totalPrice;
    }

    private List<ItemStack> getAllItems(boolean includePrices) {

        List<ItemStack> items = new ArrayList<>();
        Collection<String> itemKeys = plugin.getConfig().getConfigurationSection("shopItems").getKeys(false); // Get all shop items

        for (String k : itemKeys) {

            ItemStack is = (ItemStack) plugin.getConfig().get("shopItems." + k + ".item");
            if (is != null) {

                is.setAmount(1);
                if (includePrices) {

                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.BLACK + " ");
                    lore.add(ChatColor.GOLD + "Buy: " + ChatColor.WHITE + plugin.getConfig().get("shopItems." + k + ".price.buy") + " coins");
                    lore.add(ChatColor.GOLD + "Sell: " + ChatColor.WHITE + plugin.getConfig().get("shopItems." + k + ".price.sell") + " coins");

                    ItemMeta im = is.getItemMeta();
                    if (im != null) {
                        im.setLore(lore);
                    }
                    is.setItemMeta(im);

                }
                items.add(is);
            }
        }
        return items;
    }

    private void refreshShopMenu (int page) {
        gui.clear();
        List<ItemStack> items = getAllItems(true);
        for (int i = 45 * page; i < Math.min(getAllItems(true).size(), 45 + 45 * page); i++) {
            gui.setItem(i, getAllItems(true).get(i));
        }
        loadBottomBar();
    }

    public Inventory getInventory() {
        return gui;
    }

    void registerClick(Inventory inv, int slot, ClickType ct) {
        Bukkit.getServer().broadcastMessage(ChatColor.GREEN + (inv + " : ") + ChatColor.BLUE + (slot + " : ") + ChatColor.RED + ct.name());
        if (slot <= 44) {
            if (page * 45 + slot >= getAllItems(false).size()) {
                return;
            }

            if (orderList.size() >= 27) {
                player.sendMessage(ChatColor.RED + "This delivery is full!");
                return;
            }

            ItemStack orderedItem = getAllItems(false).get(page * 45 + slot).clone();
            if (ct.isLeftClick()) {
                orderedItem.setAmount(1);
            } else {
                orderedItem.setAmount(orderedItem.getMaxStackSize());
            }

            player.sendMessage(ChatColor.WHITE + ItemMetaStack.getItemName(orderedItem) + ChatColor.WHITE + " (x" + orderedItem.getAmount() +")" + ChatColor.GREEN + " was added to your Order");
            orderList.add(orderedItem);
        } else if (slot == 45) { // Previous Page
            if (page == 0) {
                page = getAllItems(false).size() / 45;
            } else {
                page--;
            }
        } else if (slot == 49) { // Order Delivery
            if(canOrderDelivery()) {
                player.closeInventory();
                new Delivery(orderList, player.getEyeLocation(), player).runTaskLater(plugin, 20);
            } else {
                player.sendMessage(ChatColor.RED + "Not enough coins!");
            }
        } else if (slot == 50) { // Remove Last Item
            if (orderList.size() > 0) {
                ItemStack removed = orderList.remove(orderList.size()-1);
                player.sendMessage(ChatColor.WHITE + ItemMetaStack.getItemName(removed) + ChatColor.GRAY + " (x" + removed.getAmount() +")" + ChatColor.RED + " was removed from your order");
            }
        } else if (slot == 53) { // Next Page
            page ++;
            if (page > getAllItems(false).size() / 45) {
                page = 0;
            }
        }
        refreshShopMenu(page);
    }

}