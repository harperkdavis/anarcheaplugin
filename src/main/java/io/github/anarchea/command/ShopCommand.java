package main.java.io.github.anarchea.command;

import main.java.io.github.anarchea.shop.GUIHandler;
import main.java.io.github.anarchea.shop.ShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class ShopCommand implements CommandExecutor {

    private GUIHandler shopGUI;
    private JavaPlugin plugin;

    public ShopCommand(JavaPlugin plugin, GUIHandler shopGUI) {
        this.shopGUI = shopGUI;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Shop");

            shopGUI.playerShopInventories.put(player, new ShopMenu(plugin, gui, player));

            player.openInventory(gui);

            return true;
        }

        return false;
    }
}
