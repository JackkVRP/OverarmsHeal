package me.jackk.main.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor{
	HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	
	public Player FindPlayer(String name) {
	    for(Player p : Bukkit.getOnlinePlayers()) {
	        if(p.getName().equalsIgnoreCase(name))
	            return p;
	    }
	    return null;
	}
	// Git init
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String lbl, String[] args) {
		String overarms = ChatColor.RED + "" + ChatColor.BOLD + "OVER" + ChatColor.WHITE + ChatColor.BOLD + "ARMS";
		
		if (cs.hasPermission("overarms.heal")) {
			if (args.length == 1) {
				String name = args[0];
				Player target = FindPlayer(name);
				
				if(!(target == null)) {
					cs.sendMessage(overarms + ChatColor.RESET + ChatColor.GRAY + " ≫ " + ChatColor.RED + "You healed " + ChatColor.GRAY + target.getName() + ChatColor.RED + "!");
					target.sendMessage(overarms + ChatColor.RESET + ChatColor.GRAY + " ≫ " + ChatColor.RED + "You've been healed by " + ChatColor.GRAY + cs.getName() + ChatColor.RED + "!");
					target.setHealth(20);
				} else {
					cs.sendMessage(overarms + ChatColor.RESET + ChatColor.GRAY + " ≫ " + ChatColor.RED + "Target is null!");
				}
			} else {
				if(!(args.length > 1)) {
					if(cs instanceof Player) {
						Player p = (Player) cs;
						if(cooldowns.containsKey(p.getName())) {
							int coolDownTime = 3;
							long secondsLeft = ((cooldowns.get(cs.getName())/1000) + coolDownTime) - (System.currentTimeMillis()/1000);
				            if(secondsLeft > 0) {
				            	p.sendMessage(overarms + ChatColor.RESET + ChatColor.GRAY + " ≫ " + ChatColor.RED + "Please wait " + secondsLeft + " seconds before using /heal again!");
				            	return true;
				            } else {
				            	cooldowns.remove(p.getName());
				            	cs.sendMessage(overarms + ChatColor.RESET + ChatColor.GRAY + " ≫ " + ChatColor.RED + "You've been healed!");
								p.setHealth(20);
				            }
						} else {
							cs.sendMessage(overarms + ChatColor.RESET + ChatColor.GRAY + " ≫ " + ChatColor.RED + "You've been healed!");
							p.setHealth(20);
							cooldowns.put(p.getName(), System.currentTimeMillis());
						}
					} else {
						cs.sendMessage(overarms + ChatColor.RESET + ChatColor.GRAY + " ≫ " + ChatColor.RED + "The command can only be used by players!");
					}
				} else {
					cs.sendMessage(overarms + ChatColor.RESET + ChatColor.GRAY + " ≫ " + ChatColor.RED + "Too many arguements");
				}
			} 
			return true;
		}
		return true;
	
	}
}
