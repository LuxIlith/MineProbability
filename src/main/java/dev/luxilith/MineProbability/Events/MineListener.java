package dev.luxilith.MineProbability.Events;

import dev.luxilith.MineProbability.MineProbability;
import dev.luxilith.MineProbability.Utils.ProbabilityUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

public class MineListener implements Listener {

    private final MineProbability plugin;

    public MineListener(MineProbability plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();


        if (!plugin.getConfig().getBoolean("enabled")) {
            return;
        }

        if (block.getType().toString().contains("ORE")) {
            double explosionChance = getExplosionChance();
            if (shouldExplode(explosionChance)) {
                causeExplosion(block, player);
            }
        }
    }

    private double getExplosionChance() {
        if (plugin.getConfig().getString("ProbabilityType").equals("Fixed")) {
            return plugin.getConfig().getInt("FixedProbability") / 100.0;
        } else {
            int min = plugin.getConfig().getInt("RandomProbability.Min");
            int max = plugin.getConfig().getInt("RandomProbability.Max");
            Random rand = new Random();
            return (rand.nextInt(max - min + 1) + min) / 100.0;
        }
    }

    private boolean shouldExplode(double chance) {
        Random rand = new Random();
        return rand.nextDouble() < chance;
    }

    private void causeExplosion(Block block, Player player) {
        double explosionPower = plugin.getConfig().getInt("ExplosionPower");
        boolean explodeBlocks = plugin.getConfig().getBoolean("ExplosionBreakBlocks");

        if (plugin.getConfig().getBoolean("ExplosionDamageEnabled")) {
            double damage = plugin.getConfig().getDouble("ExplosionDamage");
            player.damage(damage * 2);
        }

        if (explodeBlocks) {
            block.getWorld().createExplosion(block.getLocation(), (float) explosionPower, false, true);
        }
    }
}