package dev.luxilith.MineProbability.Commands;

import dev.luxilith.MineProbability.MineProbability;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProbabilityCommand implements CommandExecutor {

    private final MineProbability plugin;

    public ProbabilityCommand(MineProbability plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!plugin.getConfig().getBoolean("enabled")) {
            sender.sendMessage("The mining system is not enabled. Use /mine enable to enable it.");
            return false;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;

            sender.sendMessage("Currently under development.");

            return true;
        } else {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }
    }
}