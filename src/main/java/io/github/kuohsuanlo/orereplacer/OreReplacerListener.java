package io.github.kuohsuanlo.orereplacer;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class OreReplacerListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockDamageEvent(BlockDamageEvent event) {
		Block block = event.getBlock();
			
		if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
		if(!OreReplacerUtil.isValidWorld(block.getWorld())) {
			return;
		}
				
		if( OreReplacerUtil.isValidLocation(block.getLocation()) ){
			OreReplacerUtil.hideOre(block,2);
		}
		
	}
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Block block = event.getBlock();
		
		if( OreReplacerUtil.attemptAddingValidLocation(block.getLocation()) ){
			
		}
		
	}

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent event) {
    	if(event.getBlocks()==null) return;
    	ArrayList<Block> blocks = new ArrayList<Block>();
		blocks.addAll(event.getBlocks());
		for(int i=0;i<blocks.size();i++){
			Block block = blocks.get(i);
			if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
			if(!OreReplacerUtil.isValidWorld(block.getWorld())) return;
			if( OreReplacerUtil.attemptAddingValidLocation(block.getLocation()) ){
				OreReplacerUtil.replaceFirstOre(block);
			}
		}
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockPistonRetractEvent(BlockPistonRetractEvent event) {
    	if(event.getBlocks()==null) return;
		ArrayList<Block> blocks = new ArrayList<Block>();
		blocks.addAll(event.getBlocks());
		for(int i=0;i<blocks.size();i++){
			Block block = blocks.get(i);
			if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
			if(!OreReplacerUtil.isValidWorld(block.getWorld())) return;
			if( OreReplacerUtil.attemptAddingValidLocation(block.getLocation()) ){
				OreReplacerUtil.replaceFirstOre(block);
			}
		}
		
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockBreakEvent(BlockBreakEvent event) {
		Block block = event.getBlock();
		
		if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
		if(!OreReplacerUtil.isValidWorld(block.getWorld())) {
			return;
		}
		
		if( OreReplacerUtil.attemptAddingValidLocation(block.getLocation()) ){
			OreReplacerUtil.replaceFirstOre(block);
		}
    }
    /*
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockExplodeEvent(BlockExplodeEvent event) {
    	Block block = event.getBlock();
		
		//if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)) return;
		if(!OreReplacerUtil.isValidWorld(block.getWorld())) {
			return;
		}
		
		OreReplacerUtil.hideAll(block,1);
		//OreReplacerUtil.hideOre(block,1);
    }*/
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onEntityExplodeEvent(EntityExplodeEvent event) {
    	for(Block block :  event.blockList()){
    		/*
    		if(!OreReplacerUtil.isValidWorld(block.getWorld())) {
    			return;
    		}
    		
    		OreReplacerUtil.hideOre(block,1);*/
    		OreReplacerUtil.hideOre(block,2);
    		
    		if(!OreReplacerUtil.isOre(block)  &&  !OreReplacerUtil.isUndergroundBlock(block)){
    			continue;
    		}
    		if(!OreReplacerUtil.isValidWorld(block.getWorld())) {
    			continue;
    		}
    		
    		if( OreReplacerUtil.attemptAddingValidLocation(block.getLocation()) ){
    			OreReplacerUtil.replaceFirstOre(block);
    		}
    		
    	}
		
		
    }
}
