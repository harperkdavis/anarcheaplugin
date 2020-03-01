package main.java.io.github.anarchea.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemMetaStack {

    public static ItemStack createItemStack(Material m, int amount, String name, List<String> lore) {
        ItemStack i = new ItemStack(m, amount);

        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(lore);
        i.setItemMeta(im);

        return i;
    }

    public static String getItemName(ItemStack is) {
        if (is.getItemMeta().getDisplayName().equals("")) {
            return is.getType().name();
        } else {
            return is.getItemMeta().getDisplayName();
        }
    }

}
