package main.java.io.github.anarchea.shop;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;


public class Delivery extends BukkitRunnable {

    private Location deliveryLocation;
    private List<ItemStack> order;
    private Player player;

    Delivery(List<ItemStack> order, Location deliveryLocation, Player player) {
        this.order = order;
        this.deliveryLocation = deliveryLocation;
        this.player = player;
    }

    @Override
    public void run() {
        deliveryLocation.add(0, 1, 0);
        Block chestBlock = deliveryLocation.getBlock();
        deliveryLocation.add(0, 1, 0);
        deliveryLocation.getBlock().setType(Material.COBWEB);
        chestBlock.setType(Material.CHEST);
        Chest chest = (Chest) chestBlock.getState();
        for (ItemStack is : order) {
            chest.getInventory().addItem(is);
        }
        player.sendMessage(ChatColor.GREEN + "Your package has been delivered! " + ChatColor.YELLOW + "(" + deliveryLocation.getBlockX() + ", "+ deliveryLocation.getBlockZ() + ")");
    }
}
