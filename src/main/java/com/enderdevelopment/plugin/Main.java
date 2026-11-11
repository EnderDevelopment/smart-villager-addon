package com.enderdevelopment.plugin;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private SmartVillagerService smartVillagerService;

    @Override
    public void onEnable() {
        smartVillagerService = new SmartVillagerService(this);
        getServer().getPluginManager().registerEvents(new SmartVillagerListener(smartVillagerService), this);
        SmartVillagerCommand command = new SmartVillagerCommand(smartVillagerService);
        if (getCommand("smartvillager") != null) {
            getCommand("smartvillager").setExecutor(command);
            getCommand("smartvillager").setTabCompleter(command);
        }
    }

    @Override
    public void onDisable() {
        smartVillagerService = null;
    }

    public static NamespacedKey key(JavaPlugin plugin, String value) {
        return new NamespacedKey(plugin, value);
    }
}
