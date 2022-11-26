package me.tWizT3d_dreaMr.FallingCrate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import me.tWizT3d_dreaMr.FallingCrate.Objects.ConfigHandler;

public class Log {
	private static boolean d;
	private static Logger l;
	private static ConfigHandler ch;
	private static YamlConfiguration config;
	private static int num;
public static void startup(boolean de) {
	d= de;
	num=0;
	l= Bukkit.getLogger();
	DateTimeFormatter form= DateTimeFormatter.ofPattern("yyyyMMdd");
	LocalDateTime now= LocalDateTime.now();
	ch= new ConfigHandler("Logs/"+ form.format(now));
	config= ch.getConfig();
}
public static void fileOnly(String s) {
	config.set("FileOnly"+num, s);
}
public static void log(String s) {
	l.log(Level.INFO, s);
	config.set("Log"+num, s);
}
public static void warn(String s) {
	l.log(Level.WARNING, s);
	config.set("Warn"+num, s);
}
public static void error(String s) {
	l.log(Level.SEVERE, s);
	config.set("Error"+num, s);
}
public static void debug(String s) {
	if(d) {
		l.log(Level.INFO, s);
		config.set("Debug"+num, s);
	}
}
public static void saveFinal() {
	ch.SaveConfig(config);
}

}
