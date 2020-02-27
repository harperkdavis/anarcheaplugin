package main.java.io.github.anarchea.command;

import main.java.io.github.anarchea.shop.ShopGUI;
import main.java.io.github.anarchea.shop.ShopMenu;
import main.java.io.github.anarchea.utils.ItemMetaStack;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ShopCommand implements CommandExecutor {

    private ShopGUI shopGUI;
    private JavaPlugin plugin;

    public ShopCommand(JavaPlugin plugin, ShopGUI shopGUI) {
        this.shopGUI = shopGUI;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Shop");

            shopGUI.playerInventories.put(player, new ShopMenu(plugin, gui, player));

            player.openInventory(gui);

        }

        return false;
    }
}
