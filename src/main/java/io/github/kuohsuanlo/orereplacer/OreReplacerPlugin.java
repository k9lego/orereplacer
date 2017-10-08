
package io.github.kuohsuanlo.orereplacer;

import java.io.File;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
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

         
        this.reloadConfig();
     	config = this.getConfig();
     	
     	PROBABILITY_DIAMOND = config.getDouble("PROBABILITY_DIAMOND");
     	PROBABILITY_GOLD = config.getDouble("PROBABILITY_GOLD");
     	PROBABILITY_IRON = config.getDouble("PROBABILITY_IRON");
     	PROBABILITY_COAL = config.getDouble("PROBABILITY_COAL");
     	PROBABILITY_LAPIS = config.getDouble("PROBABILITY_LAPIS");
     	PROBABILITY_REDSTONE = config.getDouble("PROBABILITY_REDSTONE");
     	PROBABILITY_EMERALD = config.getDouble("PROBABILITY_EMERALD");

     	PROBABILITY_INCREASING_CONSTANT = config.getDouble("PROBABILITY_INCREASING_CONSTANT");
     	
     	REPLACING_DIAMOND = config.getBoolean("REPLACING_DIAMOND");
     	REPLACING_GOLD = config.getBoolean("REPLACING_GOLD");
     	REPLACING_IRON = config.getBoolean("REPLACING_IRON");
     	REPLACING_COAL = config.getBoolean("REPLACING_COAL");
     	REPLACING_LAPIS = config.getBoolean("REPLACING_LAPIS");
     	REPLACING_REDSTONE = config.getBoolean("REPLACING_REDSTONE");
     	REPLACINGY_EMERALD = config.getBoolean("REPLACINGY_EMERALD");
     	    
     	
     	PROBABILITY_DIAMOND = PROBABILITY_DIAMOND*PROBABILITY_INCREASING_CONSTANT;
     	PROBABILITY_GOLD = PROBABILITY_GOLD*PROBABILITY_INCREASING_CONSTANT;
     	PROBABILITY_IRON = PROBABILITY_IRON*PROBABILITY_INCREASING_CONSTANT;
     	PROBABILITY_COAL = PROBABILITY_COAL*PROBABILITY_INCREASING_CONSTANT;
     	PROBABILITY_LAPIS = PROBABILITY_LAPIS*PROBABILITY_INCREASING_CONSTANT;
     	PROBABILITY_REDSTONE = PROBABILITY_REDSTONE*PROBABILITY_INCREASING_CONSTANT;
     	PROBABILITY_EMERALD = PROBABILITY_EMERALD*PROBABILITY_INCREASING_CONSTANT;
     	
     	
    }
    @Override
    public void onEnable() {
    	
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(ORListener, this);

        CommandExecutor = new OreReplacerCommand(this);
        getCommand("orereplacer").setExecutor(CommandExecutor);
        
    	config = this.getConfig();
    	config.addDefault("version","1.0.0");
    	config.addDefault("PROBABILITY_DIAMOND",0.001);
    	config.addDefault("PROBABILITY_GOLD",0.001);
    	config.addDefault("PROBABILITY_IRON",0.006);
    	config.addDefault("PROBABILITY_COAL",0.010);
    	config.addDefault("PROBABILITY_LAPIS",0.001);
    	config.addDefault("PROBABILITY_REDSTONE",0.008);
    	config.addDefault("PROBABILITY_EMERALD",0.001);

    	config.addDefault("PROBABILITY_INCREASING_CONSTANT",3);
    	
    	config.addDefault("REPLACING_DIAMOND",true);
    	config.addDefault("REPLACINGY_EMERALD",true);
    	config.addDefault("REPLACING_GOLD",false);
    	config.addDefault("REPLACING_IRON",false);
    	config.addDefault("REPLACING_COAL",false);
    	config.addDefault("REPLACING_LAPIS",false);
    	config.addDefault("REPLACING_REDSTONE",false);
    	
    	
    	
    	
    	
    	
    	
    	    
    	
    	config.options().copyDefaults(true);
    	saveConfig();
    	
    	PROBABILITY_DIAMOND = config.getDouble("PROBABILITY_DIAMOND");
    	PROBABILITY_GOLD = config.getDouble("PROBABILITY_GOLD");
    	PROBABILITY_IRON = config.getDouble("PROBABILITY_IRON");
    	PROBABILITY_COAL = config.getDouble("PROBABILITY_COAL");
    	PROBABILITY_LAPIS = config.getDouble("PROBABILITY_LAPIS");
    	PROBABILITY_REDSTONE = config.getDouble("PROBABILITY_REDSTONE");
    	PROBABILITY_EMERALD = config.getDouble("PROBABILITY_EMERALD");

    	PROBABILITY_INCREASING_CONSTANT = config.getDouble("PROBABILITY_INCREASING_CONSTANT");
    	
    	REPLACING_DIAMOND = config.getBoolean("REPLACING_DIAMOND");
    	REPLACING_GOLD = config.getBoolean("REPLACING_GOLD");
    	REPLACING_IRON = config.getBoolean("REPLACING_IRON");
    	REPLACING_COAL = config.getBoolean("REPLACING_COAL");
    	REPLACING_LAPIS = config.getBoolean("REPLACING_LAPIS");
    	REPLACING_REDSTONE = config.getBoolean("REPLACING_REDSTONE");
    	REPLACINGY_EMERALD = config.getBoolean("REPLACINGY_EMERALD");
    	    
    	
    	PROBABILITY_DIAMOND = PROBABILITY_DIAMOND*PROBABILITY_INCREASING_CONSTANT;
    	PROBABILITY_GOLD = PROBABILITY_GOLD*PROBABILITY_INCREASING_CONSTANT;
    	PROBABILITY_IRON = PROBABILITY_IRON*PROBABILITY_INCREASING_CONSTANT;
    	PROBABILITY_COAL = PROBABILITY_COAL*PROBABILITY_INCREASING_CONSTANT;
    	PROBABILITY_LAPIS = PROBABILITY_LAPIS*PROBABILITY_INCREASING_CONSTANT;
    	PROBABILITY_REDSTONE = PROBABILITY_REDSTONE*PROBABILITY_INCREASING_CONSTANT;
    	PROBABILITY_EMERALD = PROBABILITY_EMERALD*PROBABILITY_INCREASING_CONSTANT;
    	
    	
    	
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }


}
