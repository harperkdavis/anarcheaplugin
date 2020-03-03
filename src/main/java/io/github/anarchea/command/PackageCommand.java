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

public class PackageCommand implements CommandExecutor {

    private JavaPlugin plugin;
    private GUIHandler handler;

    public PackageCommand(JavaPlugin plugin, GUIHandler guiHandler) {
        this.plugin = plugin;
        handler = guiHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                Player sendToPlayer = Bukkit.getPlayer(args[0]);
                if (sendToPlayer != null) {
                    Inventory inventoryPackage = Bukkit.createInventory(player, 27, ChatColor.DARK_GRAY + "Package to " + args[0]);

                    handler.playerPackageInventories.put(player, inventoryPackage);
                    handler.playerDelivery.put(player, sendToPlayer);

                    player.openInventory(inventoryPackage);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid Player!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid Arguments!");
            }
        }

        return false;
    }

}
