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
        is = is.clone();
        if (is.getItemMeta().getDisplayName().equals("")) {
            return toTitleCase(is.getType().name().toLowerCase());
        } else {
            return is.getItemMeta().getDisplayName();
        }
    }

    public static String toTitleCase(String input) { // Thanks, Stackoverflow!
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        input = input.replaceAll("_", " ");

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

}
