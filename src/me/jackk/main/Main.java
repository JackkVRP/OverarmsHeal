package me.jackk.main;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import me.jackk.main.commands.Heal;

public class Main extends JavaPlugin {
	
	public Main instance;
	
	public void onEnable() {
		instance = this;
		getCommand("heal2").setExecutor(new Heal());
		Bukkit.getPluginManager().addPermission(new Permission("overarms.heal"));
	}
	
	public void onDisable() {
		instance = null;
	}

}
