
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


					if(OreReplacerPlugin.REPLACING) 			arg0.sendMessage(ChatColor.RED+"[OreReplacer] : REPLACING           : "+OreReplacerPlugin.REPLACING);
					if(OreReplacerPlugin.REPLACING_DIAMOND){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[DIAMOND]  : "+OreReplacerPlugin.PROBABILITY_DIAMOND);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_DIAMOND   : "+OreReplacerPlugin.MAX_DIAMOND);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MIN_DIAMOND   : "+OreReplacerPlugin.MIN_DIAMOND);
					}
					if(OreReplacerPlugin.REPLACING_GOLD){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[GOLD]     : "+OreReplacerPlugin.PROBABILITY_GOLD);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_GOLD      : "+OreReplacerPlugin.MAX_GOLD);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MIN_GOLD   : "+OreReplacerPlugin.MIN_GOLD);
					}
					if(OreReplacerPlugin.REPLACING_IRON){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[IRON]     : "+OreReplacerPlugin.PROBABILITY_IRON);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_IRON      : "+OreReplacerPlugin.MAX_IRON);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MIN_IRON   : "+OreReplacerPlugin.MIN_IRON);
					}
					if(OreReplacerPlugin.REPLACING_COAL){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[COAL]     : "+OreReplacerPlugin.PROBABILITY_COAL);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_COAL      : "+OreReplacerPlugin.MAX_COAL);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MIN_COAL   : "+OreReplacerPlugin.MIN_COAL);
					}
					if(OreReplacerPlugin.REPLACING_LAPIS){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[LAPIS]    : "+OreReplacerPlugin.PROBABILITY_LAPIS);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_LAPIS     : "+OreReplacerPlugin.MAX_LAPIS);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MIN_LAPIS   : "+OreReplacerPlugin.MIN_LAPIS);
					}
					if(OreReplacerPlugin.REPLACING_REDSTONE){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[REDSTONE] : "+OreReplacerPlugin.PROBABILITY_REDSTONE);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_REDSTONE  : "+OreReplacerPlugin.MAX_REDSTONE);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MIN_REDSTONE   : "+OreReplacerPlugin.MIN_REDSTONE);
					}
					if(OreReplacerPlugin.REPLACINGY_EMERALD){
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : Prb[EMERALD]  : "+OreReplacerPlugin.PROBABILITY_EMERALD);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MAX_EMERALD   : "+OreReplacerPlugin.MAX_EMERALD);
						arg0.sendMessage(ChatColor.RED+"[OreReplacer] : MIN_EMERALD   : "+OreReplacerPlugin.MIN_EMERALD);
					}
					arg0.sendMessage(ChatColor.RED+"[OreReplacer] : INCREASING_CONSTANT : "+OreReplacerPlugin.PROBABILITY_INCREASING_CONSTANT);
			    	
					arg0.sendMessage(ChatColor.RED+"[OreReplacer] : reloaded!");
					return true;
				}
				else if (arg3[0].equals("toggle")  &&  arg0.hasPermission("orereplacer.toggle")){
					OreReplacerPlugin.REPLACING = !OreReplacerPlugin.REPLACING;
					arg0.sendMessage(ChatColor.RED+"[OreReplacer] : toggle "+OreReplacerPlugin.REPLACING);
					
					return true;
				}
			}
		}
		return false;
	}

}
