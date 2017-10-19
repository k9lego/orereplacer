package io.github.kuohsuanlo.orereplacer;


import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OreReplacerListener implements Listener {
	private OreReplacerPlugin orplugin;
	public OreReplacerListener(OreReplacerPlugin plugin){
		orplugin = plugin;
	}
	
	public int currentIdxDamage=0;
	private boolean isValidLocationDamaged(Location location){
    	for(int i=0;i<orplugin.eventLocationListDamaged.size();i++){
    		if(orplugin.eventLocationListDamaged.get(i).getWorld().equals(location.getWorld())  &&
    				orplugin.eventLocationListDamaged.get(i).distance(location)<0.01){
    			return false;
    		}
    	}
    	
    	
    	if(orplugin.eventLocationListDamaged.size()<OreReplacerPlugin.EventLocationListMaxDamaged){
			orplugin.eventLocationListDamaged.add(location);
			return true;
		}
		else{
			orplugin.eventLocationListDamaged.set(currentIdxDamage, location);
			currentIdxDamage++;
			currentIdxDamage%=OreReplacerPlugin.EventLocationListMaxDamaged;
			return true;
		}
    }
	
	public int currentIdx=0;
	private boolean isValidLocation(Location location){
    	for(int i=0;i<orplugin.eventLocationListMining.size();i++){
    		if(orplugin.eventLocationListMining.get(i).getWorld().equals(location.getWorld())  &&
    				orplugin.eventLocationListMining.get(i).distance(location)<0.01){
    			return false;
    		}
    	}
    	
    	
    	if(orplugin.eventLocationListMining.size()<OreReplacerPlugin.EventLocationListMaxMining){
			orplugin.eventLocationListMining.add(location);
			return true;
		}
		else{
			orplugin.eventLocationListMining.set(currentIdx, location);
			currentIdx++;
			currentIdx%=OreReplacerPlugin.EventLocationListMaxMining;
			return true;
		}
    }
    private boolean isValidType(Block block){
    	if(orplugin.REPLACING == true){
        	if( block.getType().equals(Material.STONE)  ||  isOre(block)){
            		return true;
            	}
    	}

    	return false;
    }
    private boolean isOre(Block block){
    	if(orplugin.REPLACING == true){
        	if( block.getType().equals(Material.DIAMOND_ORE) && orplugin.REPLACING_DIAMOND ||   
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
    private boolean isValidWorld(World world){
    	for(int i=0;i<orplugin.enabledWorld.size();i++){
    		if(world.equals(orplugin.enabledWorld.get(i))) return true;
    	}
    	return false;
    }
    
    /*
     * Hiding next revealing blocks, avoiding making player seeing their ores got replaced. 
     */
    @EventHandler
    public void onBlockDamageEvent(BlockDamageEvent event) {
    	if(event.getBlock()==null) return;
    	if(!isValidType(event.getBlock())) return;
    	if(!isValidLocationDamaged(event.getBlock().getLocation())) return;
    	
    	Block block = event.getBlock();
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
			if(isValidType(blockList.get(i))  &&  !isNextToAir(blockList.get(i))   &&  isOre(blockList.get(i))){  
				
				for(int j=0;j<orplugin.eventLocationListMining.size();j++){
		    		if(orplugin.eventLocationListMining.get(j).getWorld().equals(blockList.get(i).getWorld())  &&
		    				orplugin.eventLocationListMining.get(j).distance(blockList.get(i).getLocation())<0.01){
		    			
		    		}
		    		else{
		    			blockList.get(i).setType(Material.STONE);
		    		}
		    	}
			
			}
		}
    }
    
    @EventHandler
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent event) {
    	if(event.getBlocks()==null) return;
    	ArrayList<Block> blocks = new ArrayList<Block>();
		blocks.addAll(event.getBlocks());
		for(int i=0;i<blocks.size();i++){
			Block block = blocks.get(i);
			if(!isValidType(block)) return; 
			if(!isValidWorld(block.getWorld())) return;
			if( this.isValidLocation(block.getLocation()) ){
				replaceFirstOre(block);
			}
		}
		
        //plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    }
    @EventHandler
    public void onBlockPistonRetractEvent(BlockPistonRetractEvent event) {
    	if(event.getBlocks()==null) return;
		ArrayList<Block> blocks = new ArrayList<Block>();
		blocks.addAll(event.getBlocks());
		for(int i=0;i<blocks.size();i++){
			Block block = blocks.get(i);
			if(!isValidType(block)) return; 
			if(!isValidWorld(block.getWorld())) return;
			if( this.isValidLocation(block.getLocation()) ){
				replaceFirstOre(block);
			}
		}
		
        //plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    }
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
		Block block = event.getBlock();
		if(!isValidType(block)) return; 
		if(!isValidWorld(block.getWorld())) return;
		if( this.isValidLocation(block.getLocation()) ){
			replaceFirstOre(block);
		}
        //plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    }
	@EventHandler
    public void onBlockExplodeEvent(BlockExplodeEvent event) {
		Block block = event.getBlock();
		if(!isValidType(block)) return; 
		if(!isValidWorld(block.getWorld())) return;
		if( this.isValidLocation(block.getLocation()) ){
			replaceFirstOre(block);
		}
        //plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    }
    
	private int getOreNumber(Material m){
		int maxNumber =1;
		if(m.equals(Material.DIAMOND_ORE)){
			maxNumber= orplugin.MAX_DIAMOND;
		}
		if(m.equals(Material.EMERALD_ORE)){
			maxNumber= orplugin.MAX_EMERALD;
		}
		if(m.equals(Material.LAPIS_ORE)){
			maxNumber=orplugin.MAX_LAPIS;
		}
		if(m.equals(Material.REDSTONE_ORE)){
			maxNumber= orplugin.MAX_REDSTONE;
		}
		if(m.equals(Material.GOLD_ORE)){
			maxNumber= orplugin.MAX_GOLD;
		}
		if(m.equals(Material.IRON_ORE)){
			maxNumber= orplugin.MAX_IRON;
		}
		if(m.equals(Material.COAL_ORE)){
			maxNumber= orplugin.MAX_COAL;
		}
		
		int oreNumber = (int) Math.round(Math.random()*maxNumber);
		if(oreNumber==0) oreNumber=1; 
		
		return oreNumber;
	}
	private void replaceRemainedOre(Block oriBlock){
		int oreNumber = this.getOreNumber(oriBlock.getType())-1;
		Block block = oriBlock;
		for(int i=0;i<oreNumber;i++){
			
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
					
			int[] randIdx = OreReplacerUtil.generateRandomPermutation();
			
			/*
	    	 * All six nearby blocks should be marked as dirty. Otherwise the remaining ores would be removed,
	    	 * as soon as the first block is break. (Because these is only happening when these nearby blocks have passed the oreExaming.)
	    	 */
			
			// pick one of them as an ore block.
	    	for(int j=0;j<blockList.size();j++){
	    		if(isValidType(blockList.get(randIdx[j]))  &&  !isNextToAir(blockList.get(randIdx[j])) ){
	    			blockList.get(randIdx[j]).setType(oriBlock.getType());
	    			block = blockList.get(randIdx[j]);
	    			
	    			//all nearby blocks marked as dirty
	    			isValidLocation(block.getLocation().add(1,0,0));
	    			isValidLocation(block.getLocation().add(-1,0,0));
	    			isValidLocation(block.getLocation().add(0,1,0));
	    			isValidLocation(block.getLocation().add(0,-1,0));
	    			isValidLocation(block.getLocation().add(0,0,1));
	    			isValidLocation(block.getLocation().add(0,0,-1));
	    			
	    			break;
	    		}
	    	}
		}
	}
	private void replaceFirstOre(Block block){
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
    	
    	/*
    	 * All six nearby blocks should be marked as dirty. Otherwise the remaining ores would be removed,
    	 * as soon as the first block is break.  (only if it's an ore block)
    	 */
    	for(int i=0;i<blockList.size();i++){
    		if(isValidType(blockList.get(i))  &&  !isNextToAir(blockList.get(i)) ){  
    			boolean isReplacedByOre = false;
				if(isDiamond(blockList.get(i))){
					isReplacedByOre = true;
				}
				else if(isEmerald(blockList.get(i))){
					isReplacedByOre = true;
				}
				else if(isLapis(blockList.get(i))){
					isReplacedByOre = true;
				}
				else if(isGold(blockList.get(i))){
					isReplacedByOre = true;
				}
				else if(isRedStone(blockList.get(i))){
					isReplacedByOre = true;
				}
				else if(isIron(blockList.get(i))){
					isReplacedByOre = true;
				}
				else if(isCoal(blockList.get(i))){
					isReplacedByOre = true;
				}
				
				if(isReplacedByOre){
					if(isValidLocation(blockList.get(i).getLocation()))
						replaceRemainedOre(blockList.get(i));
				}
			}
    	}
    }

    private boolean isNextToAir(Block block){
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
