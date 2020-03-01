package main.java.io.github.anarchea.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ShopAddCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public ShopAddCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                if (player.isOp()) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (item.getType() != Material.AIR) {
                        String itemId = "ITEM_" + ("" + Math.random()).substring(2);
                        plugin.getConfig().set("shopItems." + itemId + ".price.buy", Double.parseDouble(args[0]));
                        plugin.getConfig().set("shopItems." + itemId + ".price.sell", Double.parseDouble(args[1]));
                        plugin.getConfig().set("shopItems." + itemId + ".item", item);
                        plugin.saveConfig();
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "There is nothing in your hand!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have permission!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Incorrect arguments!");
            }
        }
        return false;
    }
}
