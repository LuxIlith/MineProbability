package dev.luxilith.MineProbability.Commands;

import dev.luxilith.MineProbability.MineProbability;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineCommand implements CommandExecutor {

    private final MineProbability plugin;

    public MineCommand(MineProbability plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Incorrect usage. Use /mine enable or /mine disable.");
            return false;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args[0].equalsIgnoreCase("enable")) {
                plugin.getConfig().set("enabled", true);
                plugin.saveConfig();
                sender.sendMessage("The mining system has been enabled.");
            }
            else if (args[0].equalsIgnoreCase("disable")) {
                plugin.getConfig().set("enabled", false);
                plugin.saveConfig();
                sender.sendMessage("The mining system has been disabled.");
            } else {
                sender.sendMessage("Incorrect usage. Use /mine enable or /mine disable.");
                return false;
            }

            return true;
        } else {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }
    }
}