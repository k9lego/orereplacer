package io.github.kuohsuanlo.orereplacer;


import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OreReplacerListener implements Listener {
	private OreReplacerPlugin orplugin;
	public OreReplacerListener(OreReplacerPlugin plugin){
		orplugin = plugin;
	}
	@EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
		Block block = event.getBlock();
		if(shouldBeReplace(block)){
			replaceOre(block);
		}
        //plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    }
	@EventHandler
    public void onBlockExplodeEvent(BlockExplodeEvent event) {
		Block block = event.getBlock();
		if(shouldBeReplace(block)){
			replaceOre(block);
		}
        //plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    }
    private void replaceOre(Block block){
    	double x = block.getLocation().getBlockX();
    	double y = block.getLocation().getBlockY();
    	double z = block.getLocation().getBlockZ();
    	ArrayList<Block> blockList = new ArrayList<Block>();
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x+1,y,z)));
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x-1,y,z)));
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x,y+1,z)));
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x,y-1,z)));
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x,y,z+1)));
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x,y,z-1)));
    	
    	for(int i=0;i<blockList.size();i++){
    		if(shouldBeReplace(blockList.get(i))){
    			if(!nextToAir(blockList.get(i))){

    				if(isDiamond(blockList.get(i))){
    					
    				}
    				else if(isEmerald(blockList.get(i))){
    					
    				}
    				else if(isLapis(blockList.get(i))){
    					
    				}
    				else if(isGold(blockList.get(i))){
    					
    				}
    				else if(isRedStone(blockList.get(i))){
    					
    				}
    				else if(isIron(blockList.get(i))){
    					
    				}
    				else if(isCoal(blockList.get(i))){
    					
    				}
    			}

    		}
    	}
    }
    private boolean shouldBeReplace(Block block){
    	if(orplugin.REPLACING == true){
        	if( block.getType().equals(Material.STONE)  ||  
        			block.getType().equals(Material.DIAMOND_ORE) && orplugin.REPLACING_DIAMOND ||   
        			block.getType().equals(Material.EMERALD_ORE) && orplugin.REPLACINGY_EMERALD  ||   
        			block.getType().equals(Material.LAPIS_ORE) && orplugin.REPLACING_LAPIS  ||    
        			block.getType().equals(Material.REDSTONE_ORE) && orplugin.REPLACING_REDSTONE  ||    
        			block.getType().equals(Material.GOLD_ORE) && orplugin.REPLACING_GOLD  ||   
        			block.getType().equals(Material.IRON_ORE) && orplugin.REPLACING_IRON  ||   
        			block.getType().equals(Material.COAL_ORE) && orplugin.REPLACING_COAL 
            			){
            		return true;
            	}
    	}

    	return false;
    }
    private boolean nextToAir(Block block){
    	double x = block.getLocation().getBlockX();
    	double y = block.getLocation().getBlockY();
    	double z = block.getLocation().getBlockZ();
    	ArrayList<Block> blockList = new ArrayList<Block>();
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x+1,y,z)));
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x-1,y,z)));
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x,y+1,z)));
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x,y-1,z)));
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x,y,z+1)));
    	blockList.add(block.getWorld().getBlockAt(new Location(block.getWorld(),x,y,z-1)));
    	
    	for(int i=0;i<blockList.size();i++){
    		if(	blockList.get(i).getType().equals(Material.AIR)  ||  
    			blockList.get(i).getType().equals(Material.WATER)  ||  
    			blockList.get(i).getType().equals(Material.GLASS)
    			){
    			return true;
    		}
    	}
    	
    	return false;
    }
    private boolean isDiamond(Block stone){
    	double max_y = 15;
    	double min_y = 0;
    	if(!orplugin.REPLACING_DIAMOND) return false;
    	if(stone.getLocation().getBlockY()>min_y &&  stone.getLocation().getBlockY()<max_y){
    		if(Math.random()<=orplugin.PROBABILITY_DIAMOND){
    			stone.setType(Material.DIAMOND_ORE);
    			return true;
    		}
    		else if(!stone.getType().equals(Material.STONE)){
    			stone.setType(Material.STONE);
    			return false;
    		}
    	}
    	return false;
    }
    private boolean isEmerald(Block stone){
    	double max_y = 15;
    	double min_y = 0;
    	if(!orplugin.REPLACINGY_EMERALD) return false;
    	if(stone.getLocation().getBlockY()>min_y &&  stone.getLocation().getBlockY()<max_y){
    		if(Math.random()<=orplugin.PROBABILITY_EMERALD){
    			stone.setType(Material.EMERALD_ORE);
    			return true;
    		}
    		else if(!stone.getType().equals(Material.STONE)){
    			stone.setType(Material.STONE);
    			return false;
    		}
    	}
    	return false;
    }
    private boolean isLapis(Block stone){
    	double max_y = 30;
    	double min_y = 0;
    	if(!orplugin.REPLACING_LAPIS) return false;
    	if(stone.getLocation().getBlockY()>min_y &&  stone.getLocation().getBlockY()<max_y){
    		if(Math.random()<=orplugin.PROBABILITY_LAPIS){
    			stone.setType(Material.LAPIS_ORE);
    			return true;
    		}
    		else if(!stone.getType().equals(Material.STONE)){
    			stone.setType(Material.STONE);
    			return false;
    		}
    	}
    	return false;
    }
    private boolean isRedStone(Block stone){
    	double max_y = 15;
    	double min_y = 0;
    	if(!orplugin.REPLACING_REDSTONE) return false;
    	if(stone.getLocation().getBlockY()>min_y &&  stone.getLocation().getBlockY()<max_y){
    		if(Math.random()<=orplugin.PROBABILITY_REDSTONE){
    			stone.setType(Material.REDSTONE_ORE);
    			return true;
    		}
    		else if(!stone.getType().equals(Material.STONE)){
    			stone.setType(Material.STONE);
    			return false;
    		}
    	}
    	return false;
    }
    private boolean isGold(Block stone){
    	double max_y = 30;
    	double min_y = 0;
    	if(!orplugin.REPLACING_GOLD) return false;
    	if(stone.getLocation().getBlockY()>min_y &&  stone.getLocation().getBlockY()<max_y){
    		if(Math.random()<=orplugin.PROBABILITY_GOLD){
    			stone.setType(Material.GOLD_ORE);
    			return true;
    		}
    		else if(!stone.getType().equals(Material.STONE)){
    			stone.setType(Material.STONE);
    			return false;
    		}
    	}
    	return false;
    }
    private boolean isIron(Block stone){
    	double max_y = 60;
    	double min_y = 0;
    	if(!orplugin.REPLACING_IRON) return false;
    	if(stone.getLocation().getBlockY()>min_y &&  stone.getLocation().getBlockY()<max_y){
    		if(Math.random()<=orplugin.PROBABILITY_IRON){
    			stone.setType(Material.IRON_ORE);
    			return true;
    		}
    		else if(!stone.getType().equals(Material.STONE)){
    			stone.setType(Material.STONE);
    			return false;
    		}
    	}
    	return false;
    }
    private boolean isCoal(Block stone){
    	double max_y = 70;
    	double min_y = 0;
    	if(!orplugin.REPLACING_COAL) return false;
    	if(stone.getLocation().getBlockY()>min_y &&  stone.getLocation().getBlockY()<max_y){
    		if(Math.random()<=orplugin.PROBABILITY_COAL){
    			stone.setType(Material.COAL_ORE);
    			return true;
    		}
    		else if(!stone.getType().equals(Material.STONE)){
    			stone.setType(Material.STONE);
    			return false;
    		}
    	}
    	return false;
    }
}
