package me.tWizT3d_dreaMr.FallingCrate;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class colors {
    private static final Pattern pattern = Pattern.compile("(?<!\\\\)(&#[a-fA-F\\d]{6})");

    public static String formatnp(String message) {
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String color = message.substring(matcher.start() + 1, matcher.end());

            message = message.replace("&" + color, "" + ChatColor.of(color));
            matcher = pattern.matcher(message);
            
        }
        message=ChatColor.translateAlternateColorCodes('&', message);
        return message;
}

}
