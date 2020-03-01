package main.java.io.github.anarchea.command.eco;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PayCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public PayCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {

                double coins = plugin.getConfig().getDouble("playerData." + player.getUniqueId() + ".coins");

                Player recievingPlayer = Bukkit.getPlayer(args[0]);

                if (recievingPlayer != null) {
                    if (plugin.getConfig().get("playerData." + player.getUniqueId()) != null) {
                        double recievingCoins = plugin.getConfig().getDouble("playerData." + recievingPlayer.getUniqueId() + ".coins");
                        int coinsToPay = Integer.parseInt(args[1]);
                        if (coinsToPay < coins) {
                            plugin.getConfig().set("playerData." + player.getUniqueId() + ".coins", coins - coinsToPay);
                            plugin.getConfig().set("playerData." + recievingPlayer.getUniqueId() + ".coins", recievingCoins + coinsToPay);

                            player.sendMessage(ChatColor.GREEN + "You have paid " + ChatColor.GOLD + coinsToPay + " coins" + ChatColor.GREEN + " to " + recievingPlayer.getName());
                            recievingPlayer.sendMessage(ChatColor.GREEN + "You have been paid " + ChatColor.GOLD + coinsToPay + " coins" + ChatColor.GREEN + " from " + player.getName());
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid Player!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid Player!");
                }

            } else {
                player.sendMessage(ChatColor.RED + "Incorrect arguments!");
            }
        }
        return false;
    }


}
