package main.java.io.github.anarchea;

import main.java.io.github.anarchea.command.ShopAddCommand;
import main.java.io.github.anarchea.command.ShopCommand;
import main.java.io.github.anarchea.command.eco.Coins;
import main.java.io.github.anarchea.shop.ShopGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Anarchea extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new EventManager(this), this);

        ShopGUI shopGUI = new ShopGUI(this);
        Bukkit.getServer().getPluginManager().registerEvents(shopGUI, this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Anarchea successfully enabled!");

        getCommand("shop").setExecutor(new ShopCommand(this, shopGUI));
        getCommand("shopadd").setExecutor(new ShopAddCommand(this));
        getCommand("coins").setExecutor(new Coins(this));
        getCommand("c").setExecutor(new Coins(this));

        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Anarchea successfully disabled!");
    }

}
