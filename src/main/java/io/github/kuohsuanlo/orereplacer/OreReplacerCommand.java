
package io.github.kuohsuanlo.orereplacer;


import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * Handler for the /pos sample command.
 * @author SpaceManiac
 */
public class OreReplacerCommand implements CommandExecutor {
    public OreReplacerPlugin orplugin;
	public OreReplacerCommand(OreReplacerPlugin plugin){
		orplugin = plugin;
    }
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if (arg2.equalsIgnoreCase("orereplacer")) {
			
			if (arg3.length >=1 ) {
				if(arg3[0].equals("reload")  &&  arg0.hasPermission("orereplacer.reload")){
					orplugin.onReload();


					if(orplugin.REPLACING) 			arg0.sendMessage(ChatColor.RED+"[OreReplacer] : REPLACING           : "+orplugin.REPLACING);
					if(orplugin.REPLACING_DIAMOND) 	arg0.sendMessage(ChatColor.RED+"[OreReplacer] : PROBABILITY_DIAMOND : "+orplugin.PROBABILITY_DIAMOND);
					if(orplugin.REPLACING_GOLD) 	arg0.sendMessage(ChatColor.RED+"[OreReplacer] : PROBABILITY_GOLD    : "+orplugin.PROBABILITY_GOLD);
					if(orplugin.REPLACING_IRON) 	arg0.sendMessage(ChatColor.RED+"[OreReplacer] : PROBABILITY_IRON    : "+orplugin.PROBABILITY_IRON);
					if(orplugin.REPLACING_COAL) 	arg0.sendMessage(ChatColor.RED+"[OreReplacer] : PROBABILITY_COAL    : "+orplugin.PROBABILITY_COAL);
					if(orplugin.REPLACING_LAPIS) 	arg0.sendMessage(ChatColor.RED+"[OreReplacer] : PROBABILITY_LAPIS   : "+orplugin.PROBABILITY_LAPIS);
					if(orplugin.REPLACING_REDSTONE) arg0.sendMessage(ChatColor.RED+"[OreReplacer] : PROBABILITY_REDSTONE: "+orplugin.PROBABILITY_REDSTONE);
					if(orplugin.REPLACINGY_EMERALD) arg0.sendMessage(ChatColor.RED+"[OreReplacer] : PROBABILITY_EMERALD : "+orplugin.PROBABILITY_EMERALD);
													arg0.sendMessage(ChatColor.RED+"[OreReplacer] : INCREASING_CONSTANT : "+orplugin.PROBABILITY_INCREASING_CONSTANT);
			    	
					arg0.sendMessage(ChatColor.RED+"[OreReplacer] : reloaded!");
					return true;
				}
				else if (arg3[0].equals("toggle")  &&  arg0.hasPermission("orereplacer.toggle")){
					orplugin.REPLACING = !orplugin.REPLACING;
					arg0.sendMessage(ChatColor.RED+"[OreReplacer] : toggle "+orplugin.REPLACING);
					
					return true;
				}
			}
		}
		return false;
	}

}
