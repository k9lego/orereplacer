
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
					if(orplugin.REPLACING_DIAMOND){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[DIAMOND]  : "+orplugin.PROBABILITY_DIAMOND);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_DIAMOND   : "+orplugin.MAX_DIAMOND);
					}
					if(orplugin.REPLACING_GOLD){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[GOLD]     : "+orplugin.PROBABILITY_GOLD);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_GOLD      : "+orplugin.MAX_GOLD);
					}
					if(orplugin.REPLACING_IRON){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[IRON]     : "+orplugin.PROBABILITY_IRON);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_IRON      : "+orplugin.MAX_IRON);
					}
					if(orplugin.REPLACING_COAL){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[COAL]     : "+orplugin.PROBABILITY_COAL);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_COAL      : "+orplugin.MAX_COAL);
					}
					if(orplugin.REPLACING_LAPIS){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[LAPIS]    : "+orplugin.PROBABILITY_LAPIS);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_LAPIS     : "+orplugin.MAX_LAPIS);
					}
					if(orplugin.REPLACING_REDSTONE){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[REDSTONE] : "+orplugin.PROBABILITY_REDSTONE);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_REDSTONE  : "+orplugin.MAX_REDSTONE);
					}
					if(orplugin.REPLACINGY_EMERALD){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[EMERALD]  : "+orplugin.PROBABILITY_EMERALD);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_EMERALD   : "+orplugin.MAX_EMERALD);
					}
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
