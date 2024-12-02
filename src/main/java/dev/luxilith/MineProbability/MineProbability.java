package dev.luxilith.MineProbability;

import dev.luxilith.MineProbability.Commands.MineCommand;
import dev.luxilith.MineProbability.Commands.ProbabilityCommand;
import dev.luxilith.MineProbability.Events.MineListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MineProbability extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        validateConfig();
        getServer().getPluginManager().registerEvents(new MineListener(this), this);
        getCommand("mine").setExecutor(new MineCommand(this));
        getCommand("probability").setExecutor(new ProbabilityCommand(this));
        getLogger().info("MineProbability Plugin Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("MineProbability Plugin Disabled");
    }

    private void validateConfig() {
        FileConfiguration config = getConfig();

        int fixedPercentage = config.getInt("FixedProbability");
        int minRandomPercentage = config.getInt("RandomProbability.Min");
        int maxRandomPercentage = config.getInt("RandomProbability.Max");

        if (fixedPercentage < 1 || fixedPercentage > 100) {
            getLogger().warning("Fixed percentage in config.yml must be between 1 and 100.");
            config.set("FixedProbability", 50);
        }

        if (minRandomPercentage < 1 || minRandomPercentage > 100) {
            getLogger().warning("Minimum random percentage must be between 1 and 100.");
            config.set("RandomProbability.Min", 20);
        }

        if (maxRandomPercentage < 1 || maxRandomPercentage > 100 || maxRandomPercentage < minRandomPercentage) {
            getLogger().warning("Maximum random percentage must be between 1 and 100 and greater than the minimum.");
            config.set("RandomProbability.Max", 80);
        }

        if (config.getInt("ExplosionPower") < 1 || config.getInt("ExplosionPower") > 5) {
            getLogger().warning("Explosion power must be between 1 and 5.");
            config.set("ExplosionPower", 5);
        }

        if (config.getDouble("ExplosionDamage") < 1 || config.getDouble("ExplosionDamage") > 10) {
            getLogger().warning("Explosion damage must be between 1 and 10.");
            config.set("ExplosionDamage", 5.0);
        }

        saveConfig();
    }
}