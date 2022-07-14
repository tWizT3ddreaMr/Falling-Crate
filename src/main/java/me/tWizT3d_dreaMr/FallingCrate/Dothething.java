package me.tWizT3d_dreaMr.FallingCrate;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.*;
import java.util.logging.Level;

public class Dothething
  implements Listener
{
  static World world;
  static HashMap<String,Location> locs;
  
  public static boolean init() {
      FileConfiguration config = main.config;
      locs = new HashMap<>();
      List<String> namelist = new ArrayList<>(config.getConfigurationSection("Crates").getKeys(false));
      for (String key : namelist) {
          int x = config.getInt("Crates." + key + ".ChestLocation.X");
          int y = config.getInt("Crates." + key + ".ChestLocation.Y");
          int z = config.getInt("Crates." + key + ".ChestLocation.Z");
          if (Bukkit.getWorld(config.getString("Crates." + key + ".ChestLocation.World")) == null) {
              Bukkit.getLogger().log(Level.WARNING, "key" + key + " world is null, skipping");
              continue;
          }
          World world = Bukkit.getWorld(config.getString("Crates." + key + ".ChestLocation.World"));
          Location loc = new Location(world, x, y, z);
          locs.put(key, loc);

      }
      if (locs.isEmpty()) {
          Bukkit.getLogger().log(Level.SEVERE, "Chest location list is empty, please check config");
          return false;
      }
      return true;
  }
  
  public static void give(String type, Player p)
  {

    Block block = locs.get(type).getBlock();
    if(block==null||block.getType()==Material.AIR) {
    	Bukkit.getLogger().log(Level.SEVERE, "Chest for "+type+" is null. "+p.getName()+" did not get item.");
    	p.sendMessage(ChatColor.RED+"Chest does not exist.");
    	return;
    }
    ItemStack[] item = null;
    if(block.getState() instanceof DoubleChest) {
    	item=((DoubleChest)block.getState()).getInventory().getContents();
    }

    if(block.getState() instanceof Chest) {
    	item=((Chest)block.getState()).getInventory().getContents();
    }

    if(block.getState() instanceof ShulkerBox) {
    	item=((ShulkerBox)block.getState()).getInventory().getContents();
    }

    if(block.getState() instanceof Barrel) {
    	item=((Barrel)block.getState()).getInventory().getContents();
    }

      int len = item.length;
    ItemStack[] item2 = new ItemStack[len];
    int count=0,
      countb=0,
      Viable = 0;
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
    	}
    	item3[count] = item2[countb];
    	count++;
    	countb++;
    }

      if(Viable<=0) {
    	p.sendMessage(ChatColor.DARK_AQUA+"Out of Items in the chest. Type: "+ChatColor.AQUA+type);
    }
    int go = (int)Math.round(main.rand(0.0D, Viable - 1));
      if (item3[go] == null || item3[go].getType() == Material.AIR) return;
      if (main.config.getBoolean("Setting.Armor")) {
          boolean armor = false;
          for (ItemStack i : p.getInventory().getArmorContents()) {
              if (!(i == null || i.getType() == Material.AIR)) {
                  armor = true;
              }
          }

          if (armor) {
              p.sendMessage(ChatColor.RED + "Remove your armor slot. No Item for you");
              return;
		    }
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
      ItemStack FI = item3[go];
      ItemStack ret = item3[go];
      if (main.config.getBoolean("Setting.KnowlegeBook")) {
          if (FI.getType() == Material.KNOWLEDGE_BOOK) {
              ItemMeta m = FI.getItemMeta();
              List<String> l = m.getLore();
              l.add(ChatColor.GREEN + "Awarded to " + p.getName());
              FI.setItemMeta(m);
          }
      }
      p.getInventory().addItem(FI);
      FI = ret;


  }

    public static void SetChest(double d, double e, double f, String keyname, World w)
  {
      if (locs.containsKey(keyname))
          locs.replace(keyname, new Location(w, d, e, f));
      else {
          locs.put(keyname, new Location(w, d, e, f));
          main.config.addDefault("Crates." + keyname + ".Announce.AnnounceDrop", false);
          main.config.addDefault("Crates." + keyname + ".Announce.String", "&b" + keyname + " has dropped");
      }
      main.config.set("Crates." + keyname + ".ChestLocation.X", d);
      main.config.set("Crates." + keyname + ".ChestLocation.Y", e);
      main.config.set("Crates." + keyname + ".ChestLocation.Z", f);
      main.config.set("Crates." + keyname + ".ChestLocation.World", w.getName());
      List<String> blist = new ArrayList<>();
      blist.add("DIAMOND_BLOCK");
      main.config.set("Crates.Diamond.Blocks", blist);

      main.plugin.saveConfig();
  }

    public static void NonPlayer(Double x, Double y, Double z, String keyname, World w) {
        keyname = keyname.substring(0, 1).toUpperCase() + keyname.substring(1).toLowerCase();
        world = w;
        Location loc = new Location(world, x, y, z);
        @SuppressWarnings("unchecked")
        List<String> bs = (List<String>) main.config.getList("Crates." + keyname + ".Blocks");

        Material m = Material.getMaterial(bs.get(main.randint(1, bs.size()) - 1).toUpperCase());
        if (m == null) {
            Bukkit.getLogger().log(Level.SEVERE, "Material in " + keyname + " is null");
            return;
        }
        FallingBlock fb = world.spawnFallingBlock(loc, Bukkit.createBlockData(m));
        fb.setDropItem(false);
        fb.setCustomName(keyname);
        fb.setMetadata("FallingBlock", new FixedMetadataValue(main.plugin, Boolean.TRUE));
        if (!main.config.contains("Crates." + keyname + ".Announce.AnnounceDrop")) return;
        if (main.config.getBoolean("Crates." + keyname + ".Announce.AnnounceDrop")) {
            String msg = colors.formatnp(main.config.getString("Crates." + keyname + ".Announce.String"));
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {

                if (me.tWizT3d_dreaMr.FallingCrate.main.isInArena(p)) {
                    p.sendMessage(msg);
                }
            }
        }
    }

    @EventHandler
    public void Chat(AsyncPlayerChatEvent e) {
        if (e.getPlayer().hasPermission("tCrate.create") || !(me.tWizT3d_dreaMr.FallingCrate.main.mute)) {
            return;
        }
        if (me.tWizT3d_dreaMr.FallingCrate.main.isInArena(e.getPlayer())) {
            e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "Chat mute in arena active");
            e.setCancelled(true);
            return;
        }
        Set<Player> temp = new HashSet<>();
        for (Player p : e.getRecipients()) {
            if (me.tWizT3d_dreaMr.FallingCrate.main.isInArena(p) && !p.hasPermission("tCrate.create")) {

                temp.add(p);

            }

        }
        for (Player tp : temp) {
            e.getRecipients().remove(tp);
        }

    }

    @EventHandler
    public void checkBlock(EntityChangeBlockEvent e) {
        if ((e.getEntity() instanceof FallingBlock fblock)) {
            if (isFallingBlock(fblock)) {
                Block block = e.getBlock();
                block.setMetadata("PlacedBlock", new FixedMetadataValue(main.plugin, Boolean.FALSE));
                block.setMetadata("Group", new FixedMetadataValue(main.plugin, fblock.getName()));
            }
        }
    }

    public boolean isPlacedBlock(Block b) {
        List<MetadataValue> metaDataValues = b.getMetadata("PlacedBlock");
        Iterator<MetadataValue> localIterator = metaDataValues.iterator();
        if (localIterator.hasNext()) {
            MetadataValue value = localIterator.next();
            return value.asBoolean();
        }
        return true;
    }

    public boolean isFallingBlock(FallingBlock b) {
        List<MetadataValue> metaDataValues = b.getMetadata("FallingBlock");
        Iterator<MetadataValue> localIterator = metaDataValues.iterator();
        if (localIterator.hasNext()) {
            MetadataValue value = localIterator.next();
            return value.asBoolean();
        }
        return false;
    }

    public String getName(Block b) {
        List<MetadataValue> metaDataValues = b.getMetadata("Group");
        Iterator<MetadataValue> localIterator = metaDataValues.iterator();
        if (localIterator.hasNext()) {
            MetadataValue value = localIterator.next();
            return value.asString();
        }
        return null;
    }

    @EventHandler
    public void rightClickBlock(PlayerInteractEvent e) {
        if (main.config.getBoolean("Setting.KnowlegeBook")) {
            if (!(e.getItem() == null || e.getItem().getType() == null))
                if (e.getItem().getType() == Material.KNOWLEDGE_BOOK) {
                    e.setCancelled(true);
                }
        }
        if (e.getClickedBlock() == null) {
            return;
        }
        if (!e.getHand().equals(EquipmentSlot.HAND))
            return;
        Block b = e.getClickedBlock();
        if (isPlacedBlock(b)) return;
        Player p = e.getPlayer();

        String name = getName(b);
        if (name == null) {
            Bukkit.getLogger().log(Level.SEVERE, "Error with name block material: " + b.getType());
            return;
        }

        e.getClickedBlock().setType(Material.AIR);
        give(name, p);
        e.setCancelled(true);

    }


}
