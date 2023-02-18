package me.tWizT3d_dreaMr.FallingCrate;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;

import me.tWizT3d_dreaMr.FallingCrate.Objects.ConfigHandlerObject;


public class ConfigHandler {
private static HashMap<String,ConfigHandlerObject> configHandlers;
public static void Startup() {
	File dataFolder = new File(main.getPlugin().getDataFolder().toString()+"/Crates");
	String[] Files= dataFolder.list();
	
	configHandlers=new HashMap<>();
	
	for(String file : Files)
		configHandlers.put(file.replace(".yml", ""), new ConfigHandlerObject("Crates/"+file));
	configHandlers.put("UserData", new ConfigHandlerObject("UserData.yml"));
	configHandlers.put("config", new ConfigHandlerObject("config.yml"));
	configHandlers.put("Lang", new ConfigHandlerObject("Lang.yml"));
}
public static void updateConfig(String name, YamlConfiguration config) {
	if(!configHandlers.containsKey(name)) {
		Log.error("Config "+name+" not found");
		return;
	}
	Log.debug("ConfigHandler ContainsKey");
	ConfigHandlerObject cH=configHandlers.get(name);
	if(cH==null) {
		Log.error("Config "+name+" null");
		return;
	}
	cH.updateConfig(config);
}
public static YamlConfiguration getConfig(String name) {
	if(!configHandlers.containsKey(name)) {
		Log.error("Config "+name+" not found");
		return null;
	}
	Log.debug("ConfigHandler ContainsKey");
	ConfigHandlerObject cH=configHandlers.get(name);
	if(cH==null) {
		Log.error("Config "+name+" null");
		return null;
	}
	return cH.getConfig();
}
public static void ReloadConfigs() {
	configHandlers.clear();
	Startup();
}
}
