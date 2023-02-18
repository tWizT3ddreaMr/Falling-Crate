package me.tWizT3d_dreaMr.FallingCrate.Objects;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import me.tWizT3d_dreaMr.FallingCrate.Log;
import me.tWizT3d_dreaMr.FallingCrate.main;


public class ConfigHandlerObject {
    private File ConfigFile;
    private String ConfigName;
    private YamlConfiguration config;

    public ConfigHandlerObject(String ConfigName){
        this.ConfigName=ConfigName;
        ConfigFile =new File(main.getPlugin().getDataFolder().toString()+"/"+ConfigName+".yml");

        if(!ConfigFile.exists()){
        	Log.warn(ConfigName+".yml doesn't exist. Attempting to create the file");
            try {
                main.getPlugin().saveResource(ConfigName+".yml", false);
            } catch (IllegalArgumentException i) {
                try {
                	Log.log(ConfigName+".yml does not exist in the .jar");
                    ConfigFile.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    Log.error("Could not create file");
                }
            }
        }

        config= YamlConfiguration.loadConfiguration(ConfigFile);
        try {
            config.save(ConfigFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public ConfigHandlerObject(String ConfigName, String plugin){
        this.ConfigName=ConfigName;
        ConfigFile =new File(main.getPlugin().getDataFolder().getParentFile().toString()+"/"+ConfigName+".yml");
        if(!ConfigFile.exists()){
        	Log.warn(ConfigName+".yml doesn't exist. Attempting to create the file");
            try {
                main.getPlugin().saveResource(ConfigName+".yml", false);
            } catch (IllegalArgumentException i) {
                try {
                	Log.log(ConfigName+".yml does not exist in the .jar");
                    ConfigFile.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    Log.error("Could not create file");
                }
            }
        }

        config= YamlConfiguration.loadConfiguration(ConfigFile);
        try {
            config.save(ConfigFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void SaveConfig(YamlConfiguration config) {
        try {
            config.save(ConfigFile);
            this.config=config;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void updateConfig(YamlConfiguration config) {
        this.config=config;
    }
    public YamlConfiguration getConfig(){
        return config;
    }
    public String getConfigName(){
        return ConfigName;
    }
}
