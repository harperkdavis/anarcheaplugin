package main.java.io.github.anarchea.command;

import main.java.io.github.anarchea.utils.ShopConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class BookCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public BookCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack writtenBook = new ItemStack(Material.WRITABLE_BOOK, 1);
            if (args.length == 0) {
                player.getInventory().addItem(writtenBook);
                return true;
            } else {
                BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();

                DateFormat dtf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                dtf.setTimeZone(TimeZone.getTimeZone("PST"));

                Date now = new Date();

                List<String> bookText = new ArrayList<>();

                if (args[0].equalsIgnoreCase("TREATY")) {
                    bookText.add(ChatColor.GREEN + (ChatColor.BOLD + "TREATY"));
                    writtenBook.getItemMeta().setDisplayName(ChatColor.GREEN + (ChatColor.BOLD + "TREATY (" + player.getName() + ")"));
                } else if (args[0].equalsIgnoreCase("SANCTION")) {
                    bookText.add(ChatColor.RED + (ChatColor.BOLD + "SANCTION"));
                    writtenBook.getItemMeta().setDisplayName(ChatColor.RED + (ChatColor.BOLD + "SANCTION (" + player.getName() + ")"));
                } else if (args[0].equalsIgnoreCase("ULTIMATUM")) {
                    bookText.add(ChatColor.DARK_RED + (ChatColor.BOLD + "ULTIMATUM"));
                    writtenBook.getItemMeta().setDisplayName(ChatColor.DARK_RED + (ChatColor.BOLD + "ULTIMATUM (" + player.getName() + ")"));
                } else if (args[0].equalsIgnoreCase("MANDATE")) {
                    bookText.add(ChatColor.GOLD + (ChatColor.BOLD + "MANDATE"));
                    writtenBook.getItemMeta().setDisplayName(ChatColor.GOLD + (ChatColor.BOLD + "MANDATE (" + player.getName() + ")"));
                } else if (args[0].equalsIgnoreCase("DECREE")) {
                    bookText.add(ChatColor.BLUE + (ChatColor.BOLD + "DECREE"));
                    writtenBook.getItemMeta().setDisplayName(ChatColor.BLUE + (ChatColor.BOLD + "DECREE (" + player.getName() + ")"));
                } else if (args[0].equalsIgnoreCase("WAR")) {
                    bookText.add(ChatColor.DARK_GREEN + (ChatColor.BOLD + "DECLARATION \n" + ChatColor.DARK_GREEN + "OF WAR"));
                    writtenBook.getItemMeta().setDisplayName(ChatColor.DARK_GREEN + (ChatColor.BOLD + "DECLARATION OF WAR (" + player.getName() + ")"));
                } else {
                    player.sendMessage(ChatColor.RED + "Your book must be: TREATY, SANCTION, ULTIMATUM, MANDATE, DECREE, or WAR. Leave blank for blank book");
                    return false;
                }

                player.sendMessage(ChatColor.GREEN + "Created new book!");

                bookText.add(ChatColor.BLACK + "Issued by: ");
                bookText.add(ChatColor.DARK_BLUE + player.getName());
                bookText.add(ChatColor.BLACK + "Date: ");
                bookText.add(ChatColor.DARK_BLUE + dtf.format(now));

                if (args.length >= 2) {
                    bookText.add(ChatColor.BLACK + "Issued to: ");
                    bookText.add(ChatColor.DARK_BLUE + args[1]);
                }
                if (args.length >= 3) {
                    bookText.add(ChatColor.BLACK + "Priority: ");
                    if (args[2].equalsIgnoreCase("LOW")) {
                        bookText.add(ChatColor.GOLD + (ChatColor.BOLD + "LOW"));
                    } else if (args[2].equalsIgnoreCase("MEDIUM")) {
                        bookText.add(ChatColor.RED + (ChatColor.BOLD + "MEDIUM"));
                    }  else if (args[2].equalsIgnoreCase("HIGH")) {
                        bookText.add(ChatColor.DARK_RED + (ChatColor.BOLD + "! HIGH !"));
                    } else {
                        bookText.add(ChatColor.DARK_GRAY + (ChatColor.BOLD + args[2]));
                    }
                }


                bookMeta.addPage(String.join("\n", bookText));
                writtenBook.setItemMeta(bookMeta);

                player.getInventory().addItem(writtenBook);

                return true;
            }
        }

        return false;
    }
}
