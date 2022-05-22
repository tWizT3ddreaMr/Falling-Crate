package me.tWizT3d_dreaMr.FallingCrate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class main
  extends JavaPlugin
{
  public static Plugin plugin;
  public static boolean mute;
  public static FileConfiguration config;
  public void onEnable()
  {
	  	mute=false;
		config = getConfig();
		
        int version=1;
		if(!config.contains("version")) {
			if(config.getInt("version")!=version) {
				config.addDefault("Location.Arena.World", "not");
				
				config.addDefault("Location.Arena.X1", 0);
				config.addDefault("Location.Arena.Y1", 0);
				config.addDefault("Location.Arena.Z1", 0);
				
				config.addDefault("Location.Arena.X2", 0);
				config.addDefault("Location.Arena.Y2", 0);
				config.addDefault("Location.Arena.Z2", 0);
				config.addDefault("Setting.Armor", true);
				config.addDefault("Setting.KnowlegeBook", true);
				
				config.addDefault("Crates.Diamond.ChestLocation.X", 0);
				config.addDefault("Crates.Diamond.ChestLocation.Y", 0);
				config.addDefault("Crates.Diamond.ChestLocation.Z", 0);
				config.addDefault("Crates.Diamond.ChestLocation.World", "world");

				config.addDefault("Crates.Diamond.Announce.AnnounceDrop", false);
				config.addDefault("Crates.Diamond.Announce.String", "Diamond has dropped");
				
				List<String> Diamond=new ArrayList<String>();
				Diamond.add("DIAMOND_BLOCK");
				config.addDefault("Crates.Diamond.Blocks", Diamond);
				
				config.options().copyDefaults(true);
		        saveConfig();
			}
        }
        plugin = this;
		//Move to command
        if(Dothething.init())
        	Bukkit.getPluginManager().registerEvents(new Dothething(), this);
		
  }
  
  public void onDisable() {}
  
  public static double getdoub(String s)
  {
    Double d = Double.valueOf(0.0D);
    Scanner scan = new Scanner(s);
    if (scan.hasNextDouble()) {
      d = Double.valueOf(scan.nextDouble());
    }
    scan.close();
    return d.doubleValue();
  }
  
  static double rand(double d1, double d2)
  {
	if(d1==d2)
		return d1;
    Random r = new Random();
    double small = Math.min(d1, d2),
      big = Math.max(d1, d2);
    
    double randomValue = small + (big - small) * r.nextDouble();
    return randomValue;
  }  
  static int randint(int d1, int d2)
  {
	if(d1==d2)
		return d1;
    Random r = new Random();
    int small = Math.min(d1, d2),
      big = Math.max(d1, d2);
    
    int randomValue = r.nextInt(small, big);
    return randomValue;
  }
  public static boolean isInArena(Player p) {
	  
	  Location loc=p.getLocation();
	  if(!(loc.getWorld().getName().equals(config.get("Location.Arena.World")))) {
		  return false;
	  }
      double big,small, 
        x2 = config.getDouble("Location.Arena.X2"),
    	x1 = config.getDouble("Location.Arena.X1");
      

      small = Math.min(x1, x2);
      big = Math.max(x1, x2);
      
      if(!(loc.getX()>=small&&loc.getX()<=big)) {
    	  return false;
      }
      double z1 = config.getDouble("Location.Arena.Z1"),
    	 z2 = config.getDouble("Location.Arena.Z2");

      small = Math.min(z1, z2);
      big = Math.max(z1, z2);
      
      if(!(loc.getZ()>=small&&loc.getZ()<=big)) {
    	  return false;
      }
	  return true;
  }
  
  //TODO
  //Restructure commands
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {if (command.getName().equalsIgnoreCase("setfallcrate"))
  {
	  if (!(sender instanceof BlockCommandSender))
  {sender.sendMessage(ChatColor.DARK_AQUA+"No support for non-commandblocks");
  return true;
  }
	  if (args.length == 6)
      {
        double x1 = getdoub(args[0]),
          y1 = getdoub(args[1]),
          z1 = getdoub(args[2]),
          x2 = getdoub(args[3]),
          y2 = getdoub(args[4]),
          z2 = getdoub(args[5]);
        config = getConfig();
		config.set("Location.Arena.World", ((BlockCommandSender)sender).getBlock().getWorld().getName());
		config.set("Location.Arena.X1", x1);
		config.set("Location.Arena.Y1", y1);
		config.set("Location.Arena.Z1", z1);
		config.set("Location.Arena.X2", x2);
		config.set("Location.Arena.Y2", y2);
		config.set("Location.Arena.Z2", z2);
		sender.sendMessage("Area set");
		saveConfig();
        }
  //
	  return true;
  }
  if (command.getName().equalsIgnoreCase("togglefallcrateevent"))
  {   if (!(sender instanceof BlockCommandSender))
  		{sender.sendMessage(ChatColor.DARK_AQUA+"No support for non-commandblocks");return true;
  		}
	  if(mute) {
		  for(Player p:Bukkit.getServer().getOnlinePlayers()) {if(isInArena(p)) {
			  p.sendMessage(""+ChatColor.DARK_AQUA+ChatColor.BOLD+"Chat un-muted in arena");
		  }
			}
		mute= false;
		}
	    else {
			for(Player p:Bukkit.getServer().getOnlinePlayers()) {
				if(isInArena(p)) 
					p.sendMessage(""+ChatColor.DARK_AQUA+ChatColor.BOLD+"Chat muted in arena");
				
		}
		mute=true;
		}
	  return true;
  }
  if (command.getName().equalsIgnoreCase("FCChestSet"))
  {
    if (!(sender instanceof BlockCommandSender||sender instanceof ConsoleCommandSender)) {
  	  sender.sendMessage(ChatColor.DARK_AQUA+"No support for non-commandblocks");
  	  return true;
    }
    if(args.length!=4)
    {
        sender.sendMessage("incorrect syntax.");
    	return true;
    }
    Location bl=((BlockCommandSender)sender).getBlock().getLocation();

	  Bukkit.getLogger().log(Level.INFO, bl.getWorld().getName());
	  int x=bl.getBlockX()+Integer.parseInt(args[1]),
	    y=bl.getBlockY()+Integer.parseInt(args[2]),
	    z=bl.getBlockZ()+Integer.parseInt(args[3]);
	  Dothething.SetChest( x, y, z, args[0], bl.getWorld());
    return true;
   }
  if (command.getName().equalsIgnoreCase("cratesay"))
  {
	  if(sender instanceof Player) {
	  if(!((Player)sender).hasPermission("tCrate.create")) {
		  sender.sendMessage(ChatColor.RED+"No permissions");
		 return true; 
	  }
  }
	  String message="";
	  if(args.length==0) {
		  sender.sendMessage(ChatColor.RED+"Where is your message?");
		  return true;
	  }
  for(String temp: args) message=message+" "+temp;
  message=ChatColor.translateAlternateColorCodes('&', message);
	  for(Player p:Bukkit.getServer().getOnlinePlayers()) {
		    
	    	if(isInArena(p)) {
			  p.sendMessage(ChatColor.AQUA+"["+ChatColor.WHITE+ChatColor.BOLD+"Arena"+ChatColor.AQUA+"]"+colors.formatnp(message));
		  }
  }
  sender.sendMessage(ChatColor.AQUA+"You sent:");
  sender.sendMessage(ChatColor.AQUA+"["+ ChatColor.WHITE+ChatColor.BOLD+"Arena"+ChatColor.AQUA+"]"+colors.formatnp(message));
  return true;
  }
    if (command.getName().equalsIgnoreCase("fallcrate"))
    {
      if (!(sender instanceof BlockCommandSender||sender instanceof ConsoleCommandSender)) {
    	  sender.sendMessage(ChatColor.DARK_AQUA+"No support for non-commandblocks");
    	  return true;
      }
     
      
        if(args.length==1) {
        	 config = getConfig();
        	String type=args[0].substring(0,1).toUpperCase()+args[0].substring(1).toLowerCase();
        	if(!config.contains("Crates.Diamond.ChestLocation.X")) {
        		sender.sendMessage("Section does not exist");
        	}
        	double x1 = config.getDouble("Location.Arena.X1"),
	          y1 = config.getDouble("Location.Arena.Y1"),
	          z1 = config.getDouble("Location.Arena.Z1"),
	          x2 = config.getDouble("Location.Arena.X2"),
	          y2 = config.getDouble("Location.Arena.Y2"),
	          z2 = config.getDouble("Location.Arena.Z2");
	        double x = Math.round(rand(x1, x2)) + 0.5D,
	          y = Math.round(rand(y1, y2)),
	          z = Math.round(rand(z1, z2)) + 0.5D;
	        Dothething.NonPlayer(Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), type, Bukkit.getWorld(config.getString("Location.Arena.World")));
 	
        }
        else
        {
          sender.sendMessage("incorrect syntax.");
        }
      
      return true;
    }
    return false;
  }
}
