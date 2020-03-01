package main.java.io.github.anarchea.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShopConfig {

    public static String getConfigItemStack(JavaPlugin plugin, ItemStack is) {
        is = is.clone();
        is.setAmount(1);
        Collection<String> itemKeys = plugin.getConfig().getConfigurationSection("shopItems").getKeys(false);
        for (String k : itemKeys) { // Specific search
            if ((plugin.getConfig().get("shopItems." + k + ".item")).equals(is)) {
                return "shopItems." + k;
            }
        }
        return null;
    }

    public static double getCoins(JavaPlugin plugin, Player player) {
        return (double) Math.round(plugin.getConfig().getDouble("playerData." + player.getUniqueId() + ".coins") * 10) / 10;
    }

    public static void setCoins(JavaPlugin plugin, Player player, double coins) {
        plugin.getConfig().set("playerData." + player.getUniqueId() + ".coins", (double) Math.round(coins * 10) / 10);
        plugin.saveConfig();
    }


}
