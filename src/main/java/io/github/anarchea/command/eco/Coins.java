package main.java.io.github.anarchea.command.eco;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Coins implements CommandExecutor {

    private JavaPlugin plugin;

    public Coins(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            double coins = plugin.getConfig().getDouble("playerData." + player.getUniqueId() + ".coins");

            player.sendMessage(ChatColor.WHITE + "Your balance: " + ChatColor.GOLD + (coins + " coins"));
            return true;
        }
        return false;
    }
}
