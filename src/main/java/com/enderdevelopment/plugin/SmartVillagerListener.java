package com.enderdevelopment.plugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public final class SmartVillagerListener implements Listener {
    private final SmartVillagerService service;

    public SmartVillagerListener(SmartVillagerService service) {
        this.service = service;
    }

    @EventHandler
    public void onSmartVillagerDamaged(EntityDamageByEntityEvent event) {
        if (!service.isSmartVillager(event.getEntity())) {
            return;
        }
        Entity source = event.getDamager();
        if (source instanceof LivingEntity living && living != event.getEntity()) {
            ((org.bukkit.entity.Villager) event.getEntity()).setTarget(living);
        }
    }

    @EventHandler
    public void onSpawnEggUse(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND || event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        ItemStack item = event.getItem();
        if (!service.isSpawnEgg(item) || event.getClickedBlock() == null) {
            return;
        }
        event.setCancelled(true);
        service.spawn(event.getClickedBlock().getRelative(event.getBlockFace()).getLocation().add(0.5, 0, 0.5), event.getPlayer());
    }
}
