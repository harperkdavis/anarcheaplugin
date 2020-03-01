package main.java.io.github.anarchea.utils;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShopConfig {

    public static String getConfigItemStack(JavaPlugin plugin, ItemStack is) {
        Collection<String> itemKeys = plugin.getConfig().getConfigurationSection("shopItems").getKeys(false);
        for (String k : itemKeys) { // Specific search
            if ((plugin.getConfig().get("shopItems." + k + ".item")).equals(is)) {
                return "shopItems." + k;
            }
        }
        return null;
    }


}
