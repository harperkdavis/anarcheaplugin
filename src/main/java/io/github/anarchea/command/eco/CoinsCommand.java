package main.java.io.github.anarchea.command.eco;

import main.java.io.github.anarchea.utils.ShopConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CoinsCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public CoinsCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            double coins = ShopConfig.getCoins(plugin, player);

            player.sendMessage(ChatColor.WHITE + "Your balance: " + ChatColor.GOLD + (coins + " coins"));
            return true;
        }
        return false;
    }
}
