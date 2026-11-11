package com.enderdevelopment.plugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmartVillagerService {
    private final JavaPlugin plugin;
    private final org.bukkit.NamespacedKey smartVillagerKey;
    private final org.bukkit.NamespacedKey eggKey;

    public SmartVillagerService(JavaPlugin plugin) {
        this.plugin = plugin;
        smartVillagerKey = Main.key(plugin, "smart_villager");
        eggKey = Main.key(plugin, "smart_villager_egg");
    }

    public ItemStack createSpawnEgg() {
        ItemStack egg = new ItemStack(Material.VILLAGER_SPAWN_EGG);
        ItemMeta meta = egg.getItemMeta();
        if (meta == null) {
            return egg;
        }
        meta.displayName(Component.text("Smart Villager Spawn Egg", NamedTextColor.GREEN));
        meta.lore(java.util.List.of(Component.text("Reusable", NamedTextColor.GRAY)));
        meta.getPersistentDataContainer().set(eggKey, PersistentDataType.BYTE, (byte) 1);
        egg.setItemMeta(meta);
        return egg;
    }

    public boolean isSpawnEgg(ItemStack item) {
        if (item == null || item.getType() != Material.VILLAGER_SPAWN_EGG || !item.hasItemMeta()) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        return meta != null && meta.getPersistentDataContainer().has(eggKey, PersistentDataType.BYTE);
    }

    public boolean isSmartVillager(Entity entity) {
        return entity instanceof Villager villager
                && villager.getPersistentDataContainer().has(smartVillagerKey, PersistentDataType.BYTE);
    }

    public Villager spawn(Location location, Player creator) {
        Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        villager.getPersistentDataContainer().set(smartVillagerKey, PersistentDataType.BYTE, (byte) 1);
        villager.customName(Component.text("Smart Villager", NamedTextColor.GREEN));
        villager.setCustomNameVisible(true);
        villager.setAI(true);
        villager.setCanPickupItems(false);
        villager.setProfession(Villager.Profession.LIBRARIAN);
        villager.setVillagerType(Villager.Type.TAIGA);
        villager.setRemoveWhenFarAway(false);
        villager.playEffect(EntityEffect.VILLAGER_ANGRY);
        if (creator != null) {
            creator.sendMessage(Component.text("Smart Villager spawned.", NamedTextColor.GREEN));
        }
        return villager;
    }
}
