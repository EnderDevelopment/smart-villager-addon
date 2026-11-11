package com.enderdevelopment.plugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public final class SmartVillagerCommand implements CommandExecutor, TabCompleter {
    private final SmartVillagerService service;

    public SmartVillagerCommand(SmartVillagerService service) {
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Only players can use this command.", NamedTextColor.RED));
            return true;
        }
        if (args.length == 0 || args[0].equalsIgnoreCase("egg")) {
            player.getInventory().addItem(service.createSpawnEgg());
            player.sendMessage(Component.text("You received a reusable Smart Villager spawn egg.", NamedTextColor.GREEN));
            return true;
        }
        if (args[0].equalsIgnoreCase("spawn")) {
            service.spawn(player.getLocation(), player);
            return true;
        }
        player.sendMessage(Component.text("Usage: /smartvillager [egg|spawn]", NamedTextColor.YELLOW));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return List.of("egg", "spawn");
        }
        return List.of();
    }
}
