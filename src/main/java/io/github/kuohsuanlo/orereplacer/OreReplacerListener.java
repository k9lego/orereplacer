package io.github.kuohsuanlo.orereplacer;


import java.util.ArrayList;

import org.bukkit.Bukkit;
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

import net.md_5.bungee.api.ChatColor;

public class OreReplacerListener implements Listener {

	
    /*
     * Hiding next revealing blocks, avoiding making player seeing their ores got replaced. 
     */
	/*
    @EventHandler
    public void onBlockDamageEvent(BlockDamageEvent event) {
    	
    	Block block = event.getBlock();
    	if(block==null) return;
    	
    	if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
    	if(!OreReplacerUtil.isValidWorld(block.getWorld())) return;
    	if(!OreReplacerUtil.isValidLocationDamaged(block.getLocation())) return;

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
			Block blockAdj = blockList.get(i);
    		if(OreReplacerUtil.isOre(blockAdj)  ||  OreReplacerUtil.isUndergroundBlock(blockAdj)){
    			if( OreReplacerUtil.isCoverByUndergoundBlock(blockAdj) ){  
					for(int j=0;j<orplugin.eventLocationListDamaged.size();j++){
			    		if(orplugin.eventLocationListDamaged.get(j).getWorld().equals(blockList.get(i).getWorld())  &&
		    				orplugin.eventLocationListDamaged.get(j).distance(blockList.get(i).getLocation())<0.01){
			    			
			    		}
			    		else if(OreReplacerUtil.isOre(blockAdj)) {
			    				OreReplacerUtil.replaceOreToUndergroudBlock(blockList.get(i));
			    		}
			    	}
    			}	
			}
		}
    }*/
	 @EventHandler
    public void onBlockDamageEvent(BlockDamageEvent event) {
		Block block = event.getBlock();
			
		if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
		if(!OreReplacerUtil.isValidWorld(block.getWorld())) {
			return;
		}
		
		if( OreReplacerUtil.isValidLocation(block.getLocation()) ){
			OreReplacerUtil.replaceFirstOre(block);
		}
	}
	
    @EventHandler
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent event) {
    	if(event.getBlocks()==null) return;
    	ArrayList<Block> blocks = new ArrayList<Block>();
		blocks.addAll(event.getBlocks());
		for(int i=0;i<blocks.size();i++){
			Block block = blocks.get(i);
			if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
			if(!OreReplacerUtil.isValidWorld(block.getWorld())) return;
			if( OreReplacerUtil.isValidLocation(block.getLocation()) ){
				OreReplacerUtil.replaceFirstOre(block);
			}
		}
    }
    @EventHandler
    public void onBlockPistonRetractEvent(BlockPistonRetractEvent event) {
    	if(event.getBlocks()==null) return;
		ArrayList<Block> blocks = new ArrayList<Block>();
		blocks.addAll(event.getBlocks());
		for(int i=0;i<blocks.size();i++){
			Block block = blocks.get(i);
			if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
			if(!OreReplacerUtil.isValidWorld(block.getWorld())) return;
			if( OreReplacerUtil.isValidLocation(block.getLocation()) ){
				OreReplacerUtil.replaceFirstOre(block);
			}
		}
		
    }
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
		Block block = event.getBlock();
		
		if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
		if(!OreReplacerUtil.isValidWorld(block.getWorld())) {
			return;
		}
		
		if( OreReplacerUtil.isValidLocation(block.getLocation()) ){
			OreReplacerUtil.replaceFirstOre(block);
		}
    }
	@EventHandler
    public void onBlockExplodeEvent(BlockExplodeEvent event) {
		Block block = event.getBlock();
		if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
		if(!OreReplacerUtil.isValidWorld(block.getWorld())) return;
		if( OreReplacerUtil.isValidLocation(block.getLocation()) ){
			OreReplacerUtil.replaceFirstOre(block);
		}
    }
	
}
