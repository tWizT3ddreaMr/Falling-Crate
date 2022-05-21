package me.tWizT3d_dreaMr.FallingCrate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class Dothething
  implements Listener
{
  static World world;
  static HashMap<String,Location> locs;
  
  
  /*TODO
   
   Write an initialization class for locs
   
   * */
  
  
  @EventHandler
  public void Chat(AsyncPlayerChatEvent e)
  {
	  if(e.getPlayer().hasPermission("tCrate.create") || !(me.tWizT3d_dreaMr.FallingCrate.main.mute)){
		  return;
	  }if(me.tWizT3d_dreaMr.FallingCrate.main.isInArena(e.getPlayer())){
		  e.getPlayer().sendMessage(ChatColor.DARK_AQUA+"Chat mute in arena active");
		  e.setCancelled(true);
		  return;
	  }
	  Set<Player> temp=new HashSet<Player>();
	  for(Player p:e.getRecipients()) {
		  if(me.tWizT3d_dreaMr.FallingCrate.main.isInArena(p) && !p.hasPermission("tCrate.create")) {
			  
			  temp.add(p);
			 
		  }
		  
	  }for(Player tp:temp) {
		  e.getRecipients().remove(tp);
	  }
	  
  }
  @EventHandler
  public void checkBlock(EntityChangeBlockEvent e)
  {
    if ((e.getEntity() instanceof FallingBlock))
    {
      FallingBlock fblock = (FallingBlock)e.getEntity();
      if(isFallingBlock(fblock)){
    	  Block block = e.getBlock();
          block.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
          block.setMetadata("Group", new FixedMetadataValue(main.plugin, fblock.getName()));
      }
    }
  }
  public boolean isPlacedBlock(Block b)
  {
    List<MetadataValue> metaDataValues = b.getMetadata("PlacedBlock");
    Iterator<MetadataValue> localIterator = metaDataValues.iterator();
    if (localIterator.hasNext())
    {
      MetadataValue value = (MetadataValue)localIterator.next();
      return value.asBoolean();
    }
    return true;
  }
public boolean isFallingBlock(FallingBlock b)
{
  List<MetadataValue> metaDataValues = b.getMetadata("FallingBlock");
  Iterator<MetadataValue> localIterator = metaDataValues.iterator();
  if (localIterator.hasNext())
  {
    MetadataValue value = (MetadataValue)localIterator.next();
    return value.asBoolean();
  }
  return false;
}
  public String getName(Block b)
  {
    List<MetadataValue> metaDataValues = b.getMetadata("Group");
    Iterator<MetadataValue> localIterator = metaDataValues.iterator();
    if (localIterator.hasNext())
    {
      MetadataValue value = (MetadataValue)localIterator.next();
      return value.asString();
    }
    return null;
  }
  
  @EventHandler
  public void rightClickDiamondBlock(PlayerInteractEvent e)
  {
    if (e.getClickedBlock() == null) {
      return;
    }
    Block b=e.getClickedBlock();
    if(isPlacedBlock(b)) return;
    Player p = e.getPlayer();
    
    String name=getName(b);
    if(name==null) {
    	Bukkit.getLogger().log(Level.SEVERE, "Error with name block material: "+b.getType().toString());
    	return;
    }

    e.getClickedBlock().setType(Material.AIR);
    give(name, p);
    
  }
  
  public static void give(String type, Player p)
  {
	
    Block block = locs.get(type).getBlock();
    ItemStack[] item = null;
    if(block.getState() instanceof DoubleChest) {
    	item=((DoubleChest)block.getState()).getInventory().getContents();
    }else if(block.getState() instanceof Chest) {
    	item=((Chest)block.getState()).getInventory().getContents();
    }else if(block.getState() instanceof ShulkerBox) {
    	item=((ShulkerBox)block.getState()).getInventory().getContents();
    }
    int len = item.length;
    ItemStack[] item2 = new ItemStack[len];
    int count = 0;
    int countb = 0;
    int Viable = 0;
    while (count < len)
    {
      if (item[count] != null&&item[count].getType() != Material.AIR)
      {
        item2[Viable] = item[count];
        Viable++;
      }
      count++;
    }
    ItemStack[] item3 = new ItemStack[Viable+1];
    count = 0;
    while (count < Viable)
    {
    	if(item2[countb]==null||item2[countb].getType()==Material.AIR) {
    		while(item2[countb]==null||item2[countb].getType()==Material.AIR) {
    			countb++;
    		}
    	} item3[count] = item2[countb];
    	
      count++;
      countb++;
    }if(Viable<=0) {
    	p.sendMessage(ChatColor.DARK_AQUA+"Out of Items in the chest. Type: "+ChatColor.AQUA+type);
    }
    int go = (int)Math.round(main.rand(0.0D, Viable - 1));
    if (item3[go] != null&&item3[go].getType()!=Material.AIR) {
    	boolean armor=false;
    	for(ItemStack i:p.getInventory().getArmorContents()) {
    		if(!(i==null||i.getType()==Material.AIR)) {
    		armor=true;
    		}
    	}
      if(armor) {
    	  p.sendMessage(ChatColor.RED+"Remove your armor slot. No Item for you");
    	  return;
      }
      /*
       TODO
       Implement "Eating Items"
       
       if(type.equalsIgnoreCase("coal")) {
    	  if(block.getState() instanceof DoubleChest) {
	   	    ((DoubleChest)block.getState()).getInventory().setItem(((DoubleChest)block.getState()).getInventory().first(item3[go]), new ItemStack(Material.AIR,0));;
	          
	      }else 
	       if(block.getState() instanceof Chest) {
	         ((Chest)block.getState()).getInventory().setItem(((Chest)block.getState()).getInventory().first(item3[go]), new ItemStack(Material.AIR,0));;
	      }else 
	        if(block.getState() instanceof ShulkerBox) {
	         ((ShulkerBox)block.getState()).getInventory().setItem(((ShulkerBox)block.getState()).getInventory().first(item3[go]), new ItemStack(Material.AIR,0));;
	          
	      }
	    	  p.getInventory().addItem(new ItemStack[] { item3[go] });
	    	  
      }else {*/
      p.getInventory().addItem(new ItemStack[] { item3[go] });
      //}
      }
    
  }
  
  public static void NonPlayer(Double x, Double y, Double z, String keyname, World w, int cx, int cy, int cz)
  {
    keyname = keyname.substring(0, 1).toUpperCase() + keyname.substring(1).toLowerCase();
    world = w;
    Location loc = new Location(world, x.doubleValue(), y.doubleValue(), z.doubleValue());
    @SuppressWarnings("unchecked")
	List<String>bs=(List<String>)main.config.getList("Crates."+keyname+".Blocks");
    
    Material m=Material.getMaterial(bs.get(main.randint(1, bs.size())-1).toUpperCase(null));
    if(m==null) {
    	Bukkit.getLogger().log(Level.SEVERE, "Material in "+keyname+" is null");
    	return;
    }
    FallingBlock fb= world.spawnFallingBlock(loc, Bukkit.createBlockData(m));
    fb.setDropItem(false);
    fb.setCustomName(keyname);
    fb.setMetadata("FallingBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(true)));
    if(main.config.getBoolean("Crates."+keyname+".Announce.Do")) {
	    for(Player p:Bukkit.getServer().getOnlinePlayers()) {
	  
	    	if(me.tWizT3d_dreaMr.FallingCrate.main.isInArena(p)) {
	        	p.sendMessage(ChatColor.translateAlternateColorCodes('&',main.config.getString("Crates."+keyname+".Announce.String")));
		  }
	    }
    }
    }
  
  
}
