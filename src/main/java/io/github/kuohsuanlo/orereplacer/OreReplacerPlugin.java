
package io.github.kuohsuanlo.orereplacer;

import java.io.File;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class OreReplacerPlugin extends JavaPlugin {
    private final OreReplacerListener ORListener = new OreReplacerListener(this);
    private static final Logger log = Logger.getLogger("Minecraft");

    public int MAX_DIAMOND = 4;
    public int MAX_GOLD = 4;
    public int MAX_IRON = 5;
    public int MAX_COAL = 5;
    public int MAX_LAPIS = 4;
    public int MAX_REDSTONE = 6;
    public int MAX_EMERALD= 2;
    
    public double PROBABILITY_DIAMOND = 0;
    public double PROBABILITY_GOLD = 0;
    public double PROBABILITY_IRON = 0;
    public double PROBABILITY_COAL = 0;
    public double PROBABILITY_LAPIS = 0;
    public double PROBABILITY_REDSTONE = 0;
    public double PROBABILITY_EMERALD= 0;
    
    public double PROBABILITY_INCREASING_CONSTANT= 3;


    public boolean REPLACING_DIAMOND = false;
    public boolean REPLACING_GOLD = false;
    public boolean REPLACING_IRON = false;
    public boolean REPLACING_COAL = false;
    public boolean REPLACING_LAPIS = false;
    public boolean REPLACING_REDSTONE = false;
    public boolean REPLACINGY_EMERALD = false;
    
    public boolean REPLACING=true;

    private FileConfiguration config;
    private OreReplacerCommand CommandExecutor ;


    public ArrayList<Location> eventLocationList;
    public static final int EventLocationListMax = 100;
    public ArrayList<World> enabledWorld;
    
    @Override
    public void onDisable() {
        // TODO: Place any custom disable code here
        // NOTE: All registered events are automatically unregistered when a plugin is disabled
        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        getLogger().info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }
    public void onReload(){
    	PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(ORListener, this);
         
        CommandExecutor = new OreReplacerCommand(this);
        getCommand("orereplacer").setExecutor(CommandExecutor);

         
        reloadConfig();
     	config = getConfig();
     	
     	loadConfig();
     	
     	
     	
    }
    @Override
    public void onEnable() {
    	
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(ORListener, this);
        CommandExecutor = new OreReplacerCommand(this);
        getCommand("orereplacer").setExecutor(CommandExecutor);
        
        this.eventLocationList = new ArrayList<Location>();
        
    	config = getConfig();
    	config.addDefault("version","1.0.0");
    	

    	config.addDefault("ENABLED_WORLD","world,world_nether,world_the_end");
    	
    	config.addDefault("PROBABILITY_DIAMOND",0.001);
    	config.addDefault("PROBABILITY_GOLD",0.001);
    	config.addDefault("PROBABILITY_IRON",0.006);
    	config.addDefault("PROBABILITY_COAL",0.008);
    	config.addDefault("PROBABILITY_LAPIS",0.001);
    	config.addDefault("PROBABILITY_REDSTONE",0.008);
    	config.addDefault("PROBABILITY_EMERALD",0.001);
    	

    	config.addDefault("MAX_DIAMOND",4);
    	config.addDefault("MAX_GOLD",4);
    	config.addDefault("MAX_IRON",5);
    	config.addDefault("MAX_COAL",5);
    	config.addDefault("MAX_LAPIS",4);
    	config.addDefault("MAX_REDSTONE",6);
    	config.addDefault("MAX_EMERALD",2);

    	config.addDefault("PROBABILITY_INCREASING_CONSTANT",1.1);
    	
    	config.addDefault("REPLACING_DIAMOND",true);
    	config.addDefault("REPLACINGY_EMERALD",true);
    	config.addDefault("REPLACING_GOLD",false);
    	config.addDefault("REPLACING_IRON",false);
    	config.addDefault("REPLACING_COAL",false);
    	config.addDefault("REPLACING_LAPIS",false);
    	config.addDefault("REPLACING_REDSTONE",false);
    	
    	config.options().copyDefaults(true);
    	saveConfig();
    	
    	
    	loadConfig();
    	
    	
    	
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }
    private void loadConfig(){
    	enabledWorld = new  ArrayList<World> ();
    	String[] world_names = config.getString("ENABLED_WORLD").split(",");
    	for(int i=0;i<world_names.length;i++){
    		World world = this.getServer().getWorld(world_names[i]);
    		if(world!=null)
    			enabledWorld.add(world);
    	}
    	
    	PROBABILITY_DIAMOND = config.getDouble("PROBABILITY_DIAMOND");
     	PROBABILITY_GOLD = config.getDouble("PROBABILITY_GOLD");
     	PROBABILITY_IRON = config.getDouble("PROBABILITY_IRON");
     	PROBABILITY_COAL = config.getDouble("PROBABILITY_COAL");
     	PROBABILITY_LAPIS = config.getDouble("PROBABILITY_LAPIS");
     	PROBABILITY_REDSTONE = config.getDouble("PROBABILITY_REDSTONE");
     	PROBABILITY_EMERALD = config.getDouble("PROBABILITY_EMERALD");

     	MAX_DIAMOND = config.getInt("MAX_DIAMOND");
     	MAX_GOLD = config.getInt("MAX_GOLD");
     	MAX_IRON = config.getInt("MAX_IRON");
     	MAX_COAL = config.getInt("MAX_COAL");
     	MAX_LAPIS = config.getInt("MAX_LAPIS");
     	MAX_REDSTONE = config.getInt("MAX_REDSTONE");
     	MAX_EMERALD = config.getInt("MAX_EMERALD");
     	
     	PROBABILITY_INCREASING_CONSTANT = config.getDouble("PROBABILITY_INCREASING_CONSTANT");
     	
     	REPLACING_DIAMOND = config.getBoolean("REPLACING_DIAMOND");
     	REPLACING_GOLD = config.getBoolean("REPLACING_GOLD");
     	REPLACING_IRON = config.getBoolean("REPLACING_IRON");
     	REPLACING_COAL = config.getBoolean("REPLACING_COAL");
     	REPLACING_LAPIS = config.getBoolean("REPLACING_LAPIS");
     	REPLACING_REDSTONE = config.getBoolean("REPLACING_REDSTONE");
     	REPLACINGY_EMERALD = config.getBoolean("REPLACINGY_EMERALD");
     	    
     	
     	
     	PROBABILITY_DIAMOND = PROBABILITY_DIAMOND*PROBABILITY_INCREASING_CONSTANT*(2f/(MAX_DIAMOND+1));
     	PROBABILITY_GOLD = PROBABILITY_GOLD*PROBABILITY_INCREASING_CONSTANT*(2f/(MAX_GOLD+1));
     	PROBABILITY_IRON = PROBABILITY_IRON*PROBABILITY_INCREASING_CONSTANT*(2f/(MAX_IRON+1));
     	PROBABILITY_COAL = PROBABILITY_COAL*PROBABILITY_INCREASING_CONSTANT*(2f/(MAX_COAL+1));
     	PROBABILITY_LAPIS = PROBABILITY_LAPIS*PROBABILITY_INCREASING_CONSTANT*(2f/(MAX_LAPIS+1));
     	PROBABILITY_REDSTONE = PROBABILITY_REDSTONE*PROBABILITY_INCREASING_CONSTANT*(2f/(MAX_REDSTONE+1));
     	PROBABILITY_EMERALD = PROBABILITY_EMERALD*PROBABILITY_INCREASING_CONSTANT*(2f/(MAX_EMERALD+1));
     	
     	
    }

}
