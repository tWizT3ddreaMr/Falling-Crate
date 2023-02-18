package me.tWizT3d_dreaMr.FallingCrate;

import java.util.logging.*;

import org.bukkit.Bukkit;

public class Log {
	private static boolean d;
	private static Logger l;
public static void startup(boolean de) {
	d=de;
	l=Bukkit.getLogger();
}
public static void log(String s) {
	l.log(Level.INFO, s);
}
public static void warn(String s) {
	l.log(Level.WARNING, s);
}
public static void error(String s) {
	l.log(Level.SEVERE, s);
}
public static void debug(String s) {
	if(d)
		l.log(Level.INFO, s);
}
}
