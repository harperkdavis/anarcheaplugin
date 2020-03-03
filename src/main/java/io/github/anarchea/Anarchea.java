package main.java.io.github.anarchea;

import main.java.io.github.anarchea.command.PackageCommand;
import main.java.io.github.anarchea.command.ShopAddCommand;
import main.java.io.github.anarchea.command.ShopCommand;
import main.java.io.github.anarchea.command.BookCommand;
import main.java.io.github.anarchea.command.eco.CoinsCommand;
import main.java.io.github.anarchea.command.eco.PayCommand;
import main.java.io.github.anarchea.shop.GUIHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Anarchea extends JavaPlugin {

    public static double shopMultiplier = 2;

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new EventManager(this), this);

        GUIHandler guiHandler = new GUIHandler(this);
        Bukkit.getServer().getPluginManager().registerEvents(guiHandler, this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Anarchea successfully enabled!");

        getCommand("shop").setExecutor(new ShopCommand(this, guiHandler));
        getCommand("package").setExecutor(new PackageCommand(this, guiHandler));
        getCommand("shopadd").setExecutor(new ShopAddCommand(this));
        getCommand("coins").setExecutor(new CoinsCommand(this));
        getCommand("pay").setExecutor(new PayCommand(this));
        getCommand("book").setExecutor(new BookCommand(this));

        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Anarchea successfully disabled!");
    }

}
