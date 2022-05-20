package me.tWizT3d_dreaMr.FallingCrate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
  static int num;
  static World world;
  static Location Coal;
  static Location Iron;
  static Location Gold;
  static Location Lapis;
  static Location Diamond;
  static Location Emerald;
  static Location Beacon;
  static Location Wool;
  static Location Purpur;
  static Location DragonEgg;
  static String key;
  @EventHandler
  public void Chat(AsyncPlayerChatEvent e)
  {
	  if(Permission.canSetCrate.has(e.getPlayer())||!(me.tWizT3d_dreaMr.FallingCrate.main.mute)){
		  return;
	  }if(me.tWizT3d_dreaMr.FallingCrate.main.isInArena(e.getPlayer())){
		  e.getPlayer().sendMessage(ChatColor.DARK_AQUA+"Chat mute in arena active");
		  e.setCancelled(true);
		  return;
	  }
	  Set<Player> temp=new HashSet<Player>();
	  for(Player p:e.getRecipients()) {
		  if(me.tWizT3d_dreaMr.FallingCrate.main.isInArena(p)&&!(Permission.canSetCrate.has(p))) {
			  
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
      if (fblock.getBlockData().getMaterial() == Material.DIAMOND_BLOCK)
      {
        e.getBlock().setType(Material.DIAMOND_BLOCK);
        if (e.getBlock().getType() == Material.DIAMOND_BLOCK)
        {
          Block dia = e.getBlock();
          dia.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
        }
      }
      if (fblock.getBlockData().getMaterial() == Material.BEACON)
      {
        e.getBlock().setType(Material.BEACON);
        if (e.getBlock().getType() == Material.BEACON)
        {
          Block dia = e.getBlock();
          dia.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
        }
      }
      if (fblock.getBlockData().getMaterial() == Material.EMERALD_BLOCK)
      {
        e.getBlock().setType(Material.EMERALD_BLOCK);
        if (e.getBlock().getType() == Material.EMERALD_BLOCK)
        {
          Block emr = e.getBlock();
          emr.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
        }
      }
      if (fblock.getBlockData().getMaterial() == Material.LAPIS_BLOCK)
      {
        e.getBlock().setType(Material.LAPIS_BLOCK);
        if (e.getBlock().getType() == Material.LAPIS_BLOCK)
        {
          Block emr = e.getBlock();
          emr.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
        }
      }
      if (fblock.getBlockData().getMaterial() == Material.PURPUR_BLOCK)
      {
        e.getBlock().setType(Material.PURPUR_BLOCK);
        if (e.getBlock().getType() == Material.PURPUR_BLOCK)
        {
          Block emr = e.getBlock();
          emr.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
        }
      }
      if (fblock.getBlockData().getMaterial() == Material.GOLD_BLOCK)
      {
        e.getBlock().setType(Material.GOLD_BLOCK);
        if (e.getBlock().getType() == Material.GOLD_BLOCK)
        {
          Block emr = e.getBlock();
          emr.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
        }
      }
      if (fblock.getBlockData().getMaterial() == Material.IRON_BLOCK)
      {
        e.getBlock().setType(Material.IRON_BLOCK);
        if (e.getBlock().getType() == Material.IRON_BLOCK)
        {
          Block emr = e.getBlock();
          emr.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
        }
      }
      if (fblock.getBlockData().getMaterial() == Material.COAL_BLOCK)
      {
        e.getBlock().setType(Material.COAL_BLOCK);
        if (e.getBlock().getType() == Material.COAL_BLOCK)
        {
          Block emr = e.getBlock();
          emr.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
        }
      }
      if (fblock.getBlockData().getMaterial() == Material.WHITE_WOOL)
      {
        e.getBlock().setType(Material.WHITE_WOOL);
        if (e.getBlock().getType() == Material.WHITE_WOOL)
        {
          Block emr = e.getBlock();
          emr.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
        }
      }
      if (fblock.getBlockData().getMaterial() == Material.DRAGON_EGG)
      {
        e.getBlock().setType(Material.DRAGON_EGG);
        if (e.getBlock().getType() == Material.DRAGON_EGG)
        {
          Block emr = e.getBlock();
          emr.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.valueOf(false)));
        }
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
  
  @EventHandler
  public void rightClickDiamondBlock(PlayerInteractEvent e)
  {
    if (e.getClickedBlock() == null) {
      return;
    }
    Player p = e.getPlayer();
    Material m = e.getClickedBlock().getType();
    if ((m == Material.DIAMOND_BLOCK) && 
      (!isPlacedBlock(e.getClickedBlock())))
    {
      e.getClickedBlock().setType(Material.AIR);give("Diamond", p);
    }
    if ((m == Material.WHITE_WOOL) && 
      (!isPlacedBlock(e.getClickedBlock())))
    {
      e.getClickedBlock().setType(Material.AIR);give("wool", p);
    }
    if ((m == Material.BEACON) && 
      (!isPlacedBlock(e.getClickedBlock())))
    {
      e.getClickedBlock().setType(Material.AIR);give("Beacon", p);
    }
    if ((m == Material.LAPIS_BLOCK) && 
      (!isPlacedBlock(e.getClickedBlock())))
    {
      e.getClickedBlock().setType(Material.AIR);give("Lapis", p);
    }
    if ((m == Material.EMERALD_BLOCK) && 
      (!isPlacedBlock(e.getClickedBlock())))
    {
      e.getClickedBlock().setType(Material.AIR);give("Emerald", p);
    }
    if ((m == Material.GOLD_BLOCK) && 
      (!isPlacedBlock(e.getClickedBlock())))
    {
      e.getClickedBlock().setType(Material.AIR);give("Gold", p);
    }
    if ((m == Material.PURPUR_BLOCK) && 
      (!isPlacedBlock(e.getClickedBlock())))
    {
      e.getClickedBlock().setType(Material.AIR);give("Purpur", p);
    }
    if ((m == Material.IRON_BLOCK) && 
      (!isPlacedBlock(e.getClickedBlock())))
    {
      e.getClickedBlock().setType(Material.AIR);give("Iron", p);
    }
    if ((m == Material.COAL_BLOCK) && 
      (!isPlacedBlock(e.getClickedBlock())))
    {
      e.getClickedBlock().setType(Material.AIR);give("Coal", p);
    }
    if ((m == Material.DRAGON_EGG) && 
      (!isPlacedBlock(e.getClickedBlock())))
    {
      e.getClickedBlock().setType(Material.AIR);give("egg", p);
      e.setCancelled(true);
    }
  }
  
  public static void give(String type, Player p)
  {
    Block block = getLoc(type).getBlock();
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
      if(!armor) {if(type.equalsIgnoreCase("coal")) {
      if(block.getState() instanceof DoubleChest) {
    	  ((DoubleChest)block.getState()).getInventory().setItem(((DoubleChest)block.getState()).getInventory().first(item3[go]), new ItemStack(Material.AIR,0));;
          
      }else if(block.getState() instanceof Chest) {
    	  ((Chest)block.getState()).getInventory().setItem(((Chest)block.getState()).getInventory().first(item3[go]), new ItemStack(Material.AIR,0));;
      }else if(block.getState() instanceof ShulkerBox) {
    	  ((ShulkerBox)block.getState()).getInventory().setItem(((ShulkerBox)block.getState()).getInventory().first(item3[go]), new ItemStack(Material.AIR,0));;
          
      }
    	  p.getInventory().addItem(new ItemStack[] { item3[go] });
    	  
      }else {
      p.getInventory().addItem(new ItemStack[] { item3[go] });
      }
      }else {
    	  p.sendMessage(ChatColor.RED+"Remove your armor slot. No Item for you");
      }
    }
  }
  
  public static void NonPlayer(Double x, Double y, Double z, String keyname, World w, int cx, int cy, int cz)
  {
    keyname = keyname.substring(0, 1).toUpperCase() + keyname.substring(1).toLowerCase();
    key = keyname;
    world = w;
    Location loc = new Location(world, x.doubleValue(), y.doubleValue(), z.doubleValue());
    if (key.equalsIgnoreCase("Emerald"))
    {
      world.spawnFallingBlock(loc, Bukkit.createBlockData(Material.EMERALD_BLOCK)).setDropItem(false);
      Emerald = new Location(world, cx, cy, cz);
    }
    if (key.equalsIgnoreCase("Diamond"))
    {
      world.spawnFallingBlock(loc, Bukkit.createBlockData(Material.DIAMOND_BLOCK)).setDropItem(false);
      Diamond = new Location(world, cx, cy, cz);
    }
    if (key.equalsIgnoreCase("PURPUR"))
    {
      world.spawnFallingBlock(loc, Bukkit.createBlockData(Material.PURPUR_BLOCK)).setDropItem(false);
      Purpur = new Location(world, cx, cy, cz);
    }
    if (key.equalsIgnoreCase("IRON"))
    {for(Player p:Bukkit.getServer().getOnlinePlayers()) {
    	if(me.tWizT3d_dreaMr.FallingCrate.main.isInArena(p)) {
		  p.sendMessage(""+ChatColor.GRAY+ChatColor.BOLD+"IRON BLOCK DROPPED");
	  }
    }
      world.spawnFallingBlock(loc, Bukkit.createBlockData(Material.IRON_BLOCK)).setDropItem(false);
      Iron = new Location(world, cx, cy, cz);
    }
    if (key.equalsIgnoreCase("GOLD"))
    {
      world.spawnFallingBlock(loc, Bukkit.createBlockData(Material.GOLD_BLOCK)).setDropItem(false);
      Gold = new Location(world, cx, cy, cz);
    }
    if (key.equalsIgnoreCase("COAL"))
    {
      world.spawnFallingBlock(loc, Bukkit.createBlockData(Material.COAL_BLOCK)).setDropItem(false);
      Coal = new Location(world, cx, cy, cz);
    }
    if (key.equalsIgnoreCase("WOOL"))
    {
      world.spawnFallingBlock(loc, Bukkit.createBlockData(Material.WHITE_WOOL)).setDropItem(false);
      Wool = new Location(world, cx, cy, cz);
    }
    if (key.equalsIgnoreCase("BEACON"))
    {
      world.spawnFallingBlock(loc, Bukkit.createBlockData(Material.BEACON)).setDropItem(false);
      Beacon = new Location(world, cx, cy, cz);
    }
    if (key.equalsIgnoreCase("egg"))
    {
      world.spawnFallingBlock(loc, Bukkit.createBlockData(Material.DRAGON_EGG)).setDropItem(false);
      DragonEgg = new Location(world, cx, cy, cz);
    }
  }
  
  public static Location getLoc(String keyname)
  {
    Location loc = null;
    if (keyname.equalsIgnoreCase("Emerald")) {
      loc = Emerald;
    }
    if (keyname.equalsIgnoreCase("Diamond")) {
      loc = Diamond;
    }
    if (keyname.equalsIgnoreCase("Purpur")) {
      loc = Purpur;
    }
    if (keyname.equalsIgnoreCase("Iron")) {
      loc = Iron;
    }
    if (keyname.equalsIgnoreCase("Gold")) {
      loc = Gold;
    }
    if (keyname.equalsIgnoreCase("Coal")) {
      loc = Coal;
    }
    if (keyname.equalsIgnoreCase("Wool")) {
      loc = Wool;
    }
    if (keyname.equalsIgnoreCase("Beacon")) {
      loc = Beacon;
    }
    if (keyname.equalsIgnoreCase("Lapis")) {
      loc = Lapis;
    }
    if (keyname.equalsIgnoreCase("Egg")) {
      loc = DragonEgg;
    }
    return loc;
  }
}
